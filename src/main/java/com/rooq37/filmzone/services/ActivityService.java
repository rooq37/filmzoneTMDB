package com.rooq37.filmzone.services;

import com.rooq37.filmzone.activities.ActivitiesForm;
import com.rooq37.filmzone.activities.Activity;
import com.rooq37.filmzone.entities.CommentEntity;
import com.rooq37.filmzone.entities.RatingEntity;
import com.rooq37.filmzone.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityService {

    @Autowired
    private HelperService helperService;
    @Autowired
    private UserService userService;

    public ActivitiesForm getActivities(String currentUserEmail, int year){
        UserEntity currentUser = userService.getUserByEmail(currentUserEmail);
        List<Activity> activities = new ArrayList<>();
        Calendar cal = Calendar.getInstance();

        for(UserEntity followed : currentUser.getFollowed()){
            activities.addAll(followed.getRatings().stream().filter(rating -> {
                cal.setTime(rating.getDate());
                return cal.get(Calendar.YEAR) == year;
            }).map(this::getRatingAsActivity).collect(Collectors.toList()));
            activities.addAll(followed.getComments().stream().filter(comment -> {
                cal.setTime(comment.getDate());
                return cal.get(Calendar.YEAR) == year;
            }).map(this::getCommentAsActivity).collect(Collectors.toList()));
        }
        return mapToActivitiesForm(activities);
    }

    private ActivitiesForm mapToActivitiesForm(List<Activity> activities){
        ActivitiesForm activitiesForm = new ActivitiesForm();
        Calendar cal = Calendar.getInstance();
        for(Activity activity : activities){
            cal.setTime(activity.getDate());
            activitiesForm.getMonthByNumber(cal.get(Calendar.MONTH) + 1).add(activity);
        }
        activitiesForm.sortByDate();
        return activitiesForm;
    }

    public Activity getCommentAsActivity(CommentEntity comment){
        Activity activity = new Activity();
        activity.setDate(comment.getDate());
        activity.setCover(helperService.getCover(comment.getMovie()));
        activity.setMovieTitle(comment.getMovie().getTitle());
        activity.setMovieId(comment.getMovie().getId());
        String content = "Użytkownik " + comment.getUser().getNickname() + " dodał komentarz pod filmem: " + comment.getContent();
        activity.setContent(content);
        return activity;
    }

    public Activity getRatingAsActivity(RatingEntity rating){
        Activity activity = new Activity();
        activity.setDate(rating.getDate());
        activity.setCover(helperService.getCover(rating.getMovie()));
        activity.setMovieTitle(rating.getMovie().getTitle());
        activity.setMovieId(rating.getMovie().getId());
        String content = "Użytkownik " + rating.getUser().getNickname() + " ocenił film na ocenę " + rating.getValue();
        activity.setContent(content);
        return activity;
    }

}
