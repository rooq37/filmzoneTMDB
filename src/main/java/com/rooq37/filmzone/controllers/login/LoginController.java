package com.rooq37.filmzone.controllers.login;

import com.rooq37.filmzone.entities.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String displayLogin(@ModelAttribute("user") UserEntity user) {

        return "login/loginPage";
    }

}
