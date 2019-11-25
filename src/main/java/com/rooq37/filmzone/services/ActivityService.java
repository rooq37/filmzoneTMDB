package com.rooq37.filmzone.services;

import com.rooq37.filmzone.dtos.ActivitiesDTO;
import com.rooq37.filmzone.dtos.ActivityDTO;
import com.rooq37.filmzone.dtos.ImageDTO;
import com.rooq37.filmzone.entities.CommentEntity;
import com.rooq37.filmzone.entities.RatingEntity;
import com.rooq37.filmzone.entities.UserEntity;
import info.movito.themoviedbapi.model.MovieDb;
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
    private UserService userService;
    @Autowired
    private HelperService helperService;

    public ActivitiesDTO getActivities(String currentUserEmail, int year){
        UserEntity currentUser = userService.getUserByEmail(currentUserEmail);
        List<ActivityDTO> activities = new ArrayList<>();
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

    private ActivitiesDTO mapToActivitiesForm(List<ActivityDTO> activities){
        ActivitiesDTO activitiesDTO = new ActivitiesDTO();
        Calendar cal = Calendar.getInstance();
        for(ActivityDTO activityDTO : activities){
            cal.setTime(activityDTO.getDate());
            activitiesDTO.getMonthByNumber(cal.get(Calendar.MONTH) + 1).add(activityDTO);
        }
        activitiesDTO.sortByDate();
        return activitiesDTO;
    }

    public ActivityDTO getCommentAsActivity(CommentEntity comment){
        ActivityDTO activityDTO = getActivity(comment.getTmdbMovieId(), comment.getDate());
        String content = "Użytkownik " + comment.getUser().getNickname() + " dodał komentarz pod filmem: " + comment.getContent();
        activityDTO.setContent(content);
        return activityDTO;
    }

    public ActivityDTO getRatingAsActivity(RatingEntity rating){
        ActivityDTO activityDTO = getActivity(rating.getTmdbMovieId(), rating.getDate());
        String content = "Użytkownik " + rating.getUser().getNickname() + " ocenił film na ocenę " + rating.getValue();
        activityDTO.setContent(content);
        return activityDTO;
    }

    private ActivityDTO getActivity(Integer tmdbMovieId, Date date) {
        MovieDb movie = helperService.getBasicMovieDb(tmdbMovieId);
        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setDate(date);
        activityDTO.setCover(new ImageDTO(movie.getTitle(), helperService.getBasicImageUrl() + movie.getPosterPath(), "TMDB"));
        activityDTO.setMovieTitle(movie.getTitle());
        activityDTO.setMovieId(movie.getId());
        return activityDTO;
    }

}
