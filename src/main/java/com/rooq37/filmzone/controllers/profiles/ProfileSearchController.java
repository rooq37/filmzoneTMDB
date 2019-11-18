package com.rooq37.filmzone.controllers.profiles;

import com.rooq37.filmzone.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
public class ProfileSearchController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/profileSearch", method = RequestMethod.GET)
    public String displayProfileSearch(Model model,
                                       @RequestParam(value = "username", defaultValue = "") String username) {

        model.addAttribute("userList", (username.isEmpty()) ? Collections.emptyList() : userService.getUsers(username));
        return "profiles/profileSearch";
    }

}
