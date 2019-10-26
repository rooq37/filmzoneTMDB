package com.rooq37.filmzone.services;

import com.rooq37.filmzone.commons.MovieListElement;
import com.rooq37.filmzone.entities.RatingEntity;
import com.rooq37.filmzone.entities.UserEntity;
import com.rooq37.filmzone.profiles.ProfileForm;
import com.rooq37.filmzone.repositories.RatingRepository;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ProfileService {

    @Autowired
    private UserService userService;
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private HelperService helperService;

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

}
