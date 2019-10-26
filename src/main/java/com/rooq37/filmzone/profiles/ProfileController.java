package com.rooq37.filmzone.profiles;

import com.rooq37.filmzone.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @RequestMapping(value = "/myProfile", method = RequestMethod.GET)
    public String displayMyProfile(Principal principal,
                                   Model model) {

        model.addAttribute("profile", profileService.getProfile(principal.getName()));
        return "profiles/profilePage";
    }

}
