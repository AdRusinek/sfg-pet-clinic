package guru.springframework.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VetController {

    @RequestMapping({"/vets","/vets/index","/vets/index.html"})
    public String listVets(){

        // vets/index becouse first it will go to package and then to html/tf template
        return "vets/index";
    }
}
