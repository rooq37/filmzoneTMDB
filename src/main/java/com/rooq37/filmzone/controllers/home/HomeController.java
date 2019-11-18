package com.rooq37.filmzone.controllers.home;

import com.rooq37.filmzone.dtos.HomeDTO;
import com.rooq37.filmzone.services.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @Autowired
    private ViewService viewService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayHome(Model model) {

        HomeDTO homeDTO = viewService.getHome();
        model.addAttribute("homeView", homeDTO);

        return "home/homePage";
    }

}
