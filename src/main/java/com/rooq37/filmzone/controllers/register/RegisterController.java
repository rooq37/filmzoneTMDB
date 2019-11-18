package com.rooq37.filmzone.controllers.register;

import com.rooq37.filmzone.dtos.RegisterDTO;
import com.rooq37.filmzone.notifications.NotificationService;
import com.rooq37.filmzone.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String displayRegister(@ModelAttribute("registerDTO") RegisterDTO registerDTO) {

        return "register/registerPage";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute("registerDTO") RegisterDTO registerDTO){
        String message = userService.checkRegisterAbility(registerDTO);
        if(message != null){
            notificationService.addErrorMessage(message);
            return "register/registerPage";
        }else{
            message = userService.registerUser(registerDTO);
            notificationService.addInfoMessage(message);
            return "redirect:/";
        }
    }

}
