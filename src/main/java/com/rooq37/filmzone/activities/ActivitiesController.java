package com.rooq37.filmzone.activities;

import com.rooq37.filmzone.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class ActivitiesController {

    @Autowired
    private ActivityService activityService;

    @RequestMapping(value = "/activities", method = RequestMethod.GET)
    public String displayActivities(Principal principal,
                                    Model model,
                                    @RequestParam(value = "year", defaultValue = "2019") String year) {

        model.addAttribute("activitiesForm", activityService.getActivities(principal.getName(), Integer.valueOf(year)));
        model.addAttribute("selectedYear", Integer.valueOf(year));
        return "activities/activitiesPage";
    }

}
