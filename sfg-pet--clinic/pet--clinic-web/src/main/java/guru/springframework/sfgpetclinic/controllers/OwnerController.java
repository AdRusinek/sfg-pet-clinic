package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

// dzieki temu RequestMapping nad klasa n
// ie musimy specjalizowac w Rm metody pakunku, spring sam sie domysli

@RequestMapping("/owners")
@Controller
public class OwnerController {

    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";

    private final OwnerService ownerService;

    // constructor injection is the preffered way working with injected properties.
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }


    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }

    /**
     * this empty String is needed.
     * addAttribute takes "owner"  this is gonna be the name of the property inside the model
     * and it will be all owners from the findAll() method
     * ownerService.findAll() this is gonna give us a Set that we will be able to iterate over
     * @param model Spring MVC is automatically going to add a model
     *              when it calls this method to list
     * @return  to thymeleaf template
     */
//    @RequestMapping({"", "/","/index","/index.html"})
//    public String listOwners(Model model){
//
//        model.addAttribute("owners", ownerService.findAll());
//
//        return "owners/index";
//    }

    @RequestMapping("/find")
    public String findOwners(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model model) {
        if(owner.getLastName() == null) {
            owner.setLastName(""); // empty String signifies broadest possible search
        }

        // find Owner by last name
        List<Owner> results = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");

        if(results.isEmpty()) {
            // no owners found
            result.rejectValue("lastName","notFound","not found");
            return "owners/findOwners";
        } else if(results.size() == 1) {
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
        } else {
            // multiple owners found
            model.addAttribute("selections",results);
            return "owners/ownersList";
        }
    }

    @GetMapping("/new")
    public String initCreationForm(Model model) {
        model.addAttribute("owner",Owner.builder().build());

        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/new")
    public String processCreationForm(@Valid Owner owner,BindingResult result) {
        if(result.hasErrors()) {
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        } else {
            ownerService.save(owner);
            return "redirect:/owners/" + owner.getId();
        }
    }

    @GetMapping("/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable Long ownerId, Model model) {
        model.addAttribute(ownerService.findById(ownerId));
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/{ownerId}/edit")
    public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result,
                                         @PathVariable Long ownerId) {
        if (result.hasErrors()) {
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        } else {
            owner.setId(ownerId);
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }
}
