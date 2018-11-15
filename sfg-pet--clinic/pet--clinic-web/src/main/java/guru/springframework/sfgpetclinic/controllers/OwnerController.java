package guru.springframework.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// dzieki temu RequestMapping nad klasa n
// ie musimy specjalizowac w Rm metody pakunku, spring sam sie domysli

@RequestMapping("/owners")
@Controller
public class OwnerController {

    // ten pusty String jest potrzebny
    @RequestMapping({"", "/","/index","/index.html"})
    public String listOwners(){

        return "owners/index";
    }

}
