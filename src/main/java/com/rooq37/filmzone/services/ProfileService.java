package com.rooq37.filmzone.services;

import com.rooq37.filmzone.activity.Activity;
import com.rooq37.filmzone.commons.MovieListElement;
import com.rooq37.filmzone.entities.CommentEntity;
import com.rooq37.filmzone.entities.RatingEntity;
import com.rooq37.filmzone.entities.UserEntity;
import com.rooq37.filmzone.profiles.ProfileForm;
import com.rooq37.filmzone.repositories.RatingRepository;
import com.rooq37.filmzone.repositories.UserRepository;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileService {

    @Autowired
    private UserService userService;
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private HelperService helperService;
    @Autowired
    private UserRepository userRepository;

    public ProfileForm getProfile(String userEmail){
        ProfileForm profile = new ProfileForm();
        UserEntity user = userService.getUserByEmail(userEmail);

        profile.setNickname(user.getNickname());
        profile.setRegisterDate(user.getRegisterDate());
        profile.setNumberOfRatedMovies(ratingRepository.countByUserAndValueGreaterThan(user, 0));
        profile.setNumberOfComments(user.getComments().size());
        profile.setRatedMovies(getRatedMovies(user));
        return profile;
    }

    private List<Pair<MovieListElement, Integer>> getRatedMovies(UserEntity user){
        List<Pair<MovieListElement, Integer>> resultList = new ArrayList<>();

        for(RatingEntity rating : user.getRatings())
            resultList.add(new Pair<>(helperService.getMovieListElement(rating.getMovie()), rating.getValue()));

        resultList.sort((o1, o2) -> -1 * Integer.compare(o1.getValue(), o2.getValue()));
        return resultList;
    }

    public PagedListHolder<Activity> getActivities(String userEmail, int pageSize){
        UserEntity user = userService.getUserByEmail(userEmail);

        List<Activity> activities = new ArrayList<>();

        for(CommentEntity comment : user.getComments())
            activities.add(getCommentAsActivity(comment));

        for(RatingEntity rating : user.getRatings())
            activities.add(getRatingAsActivity(rating));

        activities.sort(Activity.dateComparator.reversed());

        PagedListHolder<Activity> pagedListHolder = new PagedListHolder<>(activities);
        pagedListHolder.setPageSize(pageSize);
        pagedListHolder.setPage(0);

        return pagedListHolder;
    }

    private Activity getCommentAsActivity(CommentEntity comment){
        Activity activity = new Activity();
        activity.setDate(comment.getDate());
        activity.setCover(helperService.getCover(comment.getMovie()));
        activity.setMovieTitle(comment.getMovie().getTitle());
        activity.setMovieId(comment.getMovie().getId());
        String content = "Użytkownik " + comment.getUser().getNickname() + " dodał komentarz pod filmem: " + comment.getContent();
        activity.setContent(content);
        return activity;
    }

    private Activity getRatingAsActivity(RatingEntity rating){
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
