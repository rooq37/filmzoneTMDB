package com.rooq37.filmzone.services;

import com.rooq37.filmzone.dtos.ActivityDTO;
import com.rooq37.filmzone.dtos.MovieSimpleDTO;
import com.rooq37.filmzone.entities.CommentEntity;
import com.rooq37.filmzone.entities.RatingEntity;
import com.rooq37.filmzone.entities.UserEntity;
import com.rooq37.filmzone.mappers.MovieSimpleMapper;
import com.rooq37.filmzone.dtos.ProfileDTO;
import com.rooq37.filmzone.repositories.RatingRepository;
import com.rooq37.filmzone.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProfileService {

    @Autowired
    private UserService userService;
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private HelperService helperService;

    public ProfileDTO getProfile(String userEmail){
        UserEntity user = userService.getUserByEmail(userEmail);
        ProfileDTO profile = getBasicProfile(user);

        profile.setRatedMovies(getRatedMovies(user));
        profile.setFollowed(user.getFollowed().stream().map(this::getBasicProfile).collect(Collectors.toList()));
        profile.setFollowers(user.getFollowers().stream().map(this::getBasicProfile).collect(Collectors.toList()));
        return profile;
    }

    private ProfileDTO getBasicProfile(UserEntity user){
        ProfileDTO profile = new ProfileDTO();
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

    private List<AbstractMap.SimpleEntry<MovieSimpleDTO, Integer>> getRatedMovies(UserEntity user){
        List<AbstractMap.SimpleEntry<MovieSimpleDTO, Integer>> resultList = new ArrayList<>();

        for(RatingEntity rating : user.getRatings())
            if(rating.getValue() > 0)
                resultList.add(new AbstractMap.SimpleEntry<>(new MovieSimpleMapper(helperService.getBasicMovieDb(rating.getTmdbMovieId())).getMovieSimpleDTO(), rating.getValue()));

        resultList.sort((o1, o2) -> -1 * Integer.compare(o1.getValue(), o2.getValue()));
        return resultList;
    }

    public PagedListHolder<ActivityDTO> getActivities(String userEmail, int pageSize){
        UserEntity user = userService.getUserByEmail(userEmail);

        List<ActivityDTO> activities = new ArrayList<>();

        for(CommentEntity comment : user.getComments())
            activities.add(activityService.getCommentAsActivity(comment));

        for(RatingEntity rating : user.getRatings())
            activities.add(activityService.getRatingAsActivity(rating));

        activities.sort(ActivityDTO.dateComparator.reversed());

        PagedListHolder<ActivityDTO> pagedListHolder = new PagedListHolder<>(activities);
        pagedListHolder.setPageSize(pageSize);
        pagedListHolder.setPage(0);

        return pagedListHolder;
    }

    public void blockUser(Long userId, String reason, Date tillDate){
        Optional<UserEntity> user = userRepository.findById(userId);
        if(user.isPresent()){
            UserEntity userEntity = user.get();
            userEntity.setBlockedTill(tillDate);
            userEntity.setBlockReason(reason);
        }
    }

}
