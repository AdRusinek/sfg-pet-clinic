package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// dzieki temu RequestMapping nad klasa n
// ie musimy specjalizowac w Rm metody pakunku, spring sam sie domysli

@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    // constructor injection is the preffered way working with injected properties.
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
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
    @RequestMapping({"", "/","/index","/index.html"})
    public String listOwners(Model model){

        model.addAttribute("owners", ownerService.findAll());

        return "owners/index";
    }

    @RequestMapping("/find")
    public String findOwners() {

        return "notimplemented";
    }


}
