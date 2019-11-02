package com.rooq37.filmzone.services;

import com.rooq37.filmzone.activities.Activity;
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
import java.util.stream.Collectors;

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
    @Autowired
    private ActivityService activityService;

    public ProfileForm getProfile(String userEmail){
        UserEntity user = userService.getUserByEmail(userEmail);
        ProfileForm profile = getBasicProfile(user);

        profile.setRatedMovies(getRatedMovies(user));
        profile.setFollowed(user.getFollowed().stream().map(this::getBasicProfile).collect(Collectors.toList()));
        profile.setFollowers(user.getFollowers().stream().map(this::getBasicProfile).collect(Collectors.toList()));
        return profile;
    }

    private ProfileForm getBasicProfile(UserEntity user){
        ProfileForm profile = new ProfileForm();
        profile.setId(user.getId());
        profile.setNickname(user.getNickname());
        profile.setEmail(user.getEmail());
        profile.setRegisterDate(user.getRegisterDate());
        profile.setNumberOfRatedMovies(ratingRepository.countByUserAndValueGreaterThan(user, 0));
        profile.setNumberOfComments(user.getComments().size());
        return profile;
    }

    public boolean isUserAlreadyFollowed(String currentUserEmail, String anotherUserEmail){
        UserEntity user = userService.getUserByEmail(currentUserEmail);
        for(UserEntity followed : user.getFollowed())
            if(followed.getEmail().equals(anotherUserEmail))
                return true;

        return false;
    }

    public boolean followUser(String currentUserEmail, String anotherUserEmail){
        if(!isUserAlreadyFollowed(currentUserEmail, anotherUserEmail)){
            UserEntity user = userService.getUserByEmail(currentUserEmail);
            UserEntity anotherUser = userService.getUserByEmail(anotherUserEmail);
            user.getFollowed().add(anotherUser);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public boolean unfollowUser(String currentUserEmail, String anotherUserEmail){
        if(isUserAlreadyFollowed(currentUserEmail, anotherUserEmail)){
            UserEntity user = userService.getUserByEmail(currentUserEmail);
            UserEntity anotherUser = userService.getUserByEmail(anotherUserEmail);
            user.getFollowed().remove(anotherUser);
            userRepository.save(user);
            return true;
        }
        return false;
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
            activities.add(activityService.getCommentAsActivity(comment));

        for(RatingEntity rating : user.getRatings())
            activities.add(activityService.getRatingAsActivity(rating));

        activities.sort(Activity.dateComparator.reversed());

        PagedListHolder<Activity> pagedListHolder = new PagedListHolder<>(activities);
        pagedListHolder.setPageSize(pageSize);
        pagedListHolder.setPage(0);

        return pagedListHolder;
    }

}
