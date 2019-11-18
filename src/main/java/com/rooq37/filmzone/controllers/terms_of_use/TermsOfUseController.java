package com.rooq37.filmzone.controllers.terms_of_use;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TermsOfUseController {

    @RequestMapping(value = "/terms_of_use", method = RequestMethod.GET)
    public String displayTermsOfUse() {

        return "terms_of_use/termsOfUsePage";
    }

}
