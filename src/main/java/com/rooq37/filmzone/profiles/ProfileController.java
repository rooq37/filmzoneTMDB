package com.rooq37.filmzone.profiles;

import com.rooq37.filmzone.activity.Activity;
import com.rooq37.filmzone.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @RequestMapping(value = "/myProfile", method = RequestMethod.GET)
    public String displayMyProfile(Principal principal,
                                   Model model,
                                   @RequestParam(value = "desiredPageSize", defaultValue = "10") int desiredPageSize) {

        ProfileForm profile = profileService.getProfile(principal.getName());
        PagedListHolder<Activity> activities = profileService.getActivities(principal.getName(), desiredPageSize);
        profile.setActivities(activities);

        String anchor = (desiredPageSize == 10) ? "" : "#activity_" + (activities.getPageSize() - 11);

        model.addAttribute("profile", profile);
        model.addAttribute("currentPageSize", desiredPageSize);
        return "profiles/profilePage";
    }

}
