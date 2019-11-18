package com.rooq37.filmzone.controllers.profiles;

import com.rooq37.filmzone.activities.Activity;
import com.rooq37.filmzone.dtos.ProfileDTO;
import com.rooq37.filmzone.entities.UserEntity;
import com.rooq37.filmzone.notifications.NotificationService;
import com.rooq37.filmzone.services.ProfileService;
import com.rooq37.filmzone.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ProfileController {

    @Autowired
    private ProfileService profileService;
    @Autowired
    private UserService userService;
    @Autowired
    private NotificationService notificationService;

    @RequestMapping(value = "/myProfile", method = RequestMethod.GET)
    public String displayMyProfile(Principal principal,
                                   Model model,
                                   @RequestParam(value = "desiredPageSize", defaultValue = "10") int desiredPageSize) {

        ProfileDTO profile = profileService.getProfile(principal.getName());
        PagedListHolder<Activity> activities = profileService.getActivities(principal.getName(), desiredPageSize);
        profile.setActivities(activities);

        String anchor = (desiredPageSize == 10) ? "" : "#activity_" + (activities.getPageSize() - 11);

        model.addAttribute("profile", profile);
        model.addAttribute("currentPageSize", desiredPageSize);
        model.addAttribute("isCurrentUserProfile", true);
        return "profiles/profilePage";
    }

    @RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
    public String displayProfile(Model model,
                                 Principal principal,
                                 @PathVariable Long id,
                                 @RequestParam(value = "desiredPageSize", defaultValue = "10") int desiredPageSize) {

        String userEmail = userService.getUserEmailById(id);
        ProfileDTO profile = profileService.getProfile(userEmail);
        PagedListHolder<Activity> activities = profileService.getActivities(userEmail, desiredPageSize);
        profile.setActivities(activities);

        String anchor = (desiredPageSize == 10) ? "" : "#activity_" + (activities.getPageSize() - 11);

        model.addAttribute("profile", profile);
        model.addAttribute("currentPageSize", desiredPageSize);
        model.addAttribute("isCurrentUserProfile", profile.getEmail().equals(principal.getName()));
        model.addAttribute("isFollowedByCurrentUser", profileService.isUserAlreadyFollowed(principal.getName(), profile.getEmail()));

        return "profiles/profilePage";
    }

    @RequestMapping(value = "/followUser", method = RequestMethod.POST)
    public String followUser(Principal principal,
                             @RequestParam(value = "userEmail") String userEmail,
                             @RequestParam(value = "action") String action) {

        UserEntity anotherUser = userService.getUserByEmail(userEmail);
        if(action.equals("follow")){
            if(profileService.followUser(principal.getName(), userEmail)){
                notificationService.addInfoMessage("Od teraz obserwujesz użytkownika " + anotherUser.getNickname() + ".");
            }else{
                notificationService.addErrorMessage("Już obserwujesz tego użytkownika.");
            }
        }else if(action.equals("unfollow")){
            if(profileService.unfollowUser(principal.getName(), userEmail)){
                notificationService.addInfoMessage("Już nie obserwujesz użytkownika " + anotherUser.getNickname() + ".");
            }else {
                notificationService.addErrorMessage("Nie obserwujesz tego użytkownika.");
            }
        }

        return "redirect:/profile/" + anotherUser.getId();
    }

    @RequestMapping(value = "/blockUser", method = RequestMethod.POST)
    public String followUser(Principal principal,
                             @RequestParam(value = "userId") Long userId,
                             @RequestParam(value = "reason") String reason,
                             @RequestParam(value = "tillDate") String date) {

        UserEntity user = userService.getUserByEmail(userService.getUserEmailById(userId));
        Date tillDate = null;
        try {
            tillDate = new SimpleDateFormat("dd-MM-yyyy").parse(date);
        } catch (ParseException e) {
            notificationService.addErrorMessage("Błędny format daty!");
            return "redirect:/profile/" + userId;
        }
        profileService.blockUser(userId, reason, tillDate);
        notificationService.addInfoMessage("Użytkownik o nazwie " + user.getNickname() + " został zablokowany do " + date + ".");

        return "redirect:/profile/" + userId;
    }

}
