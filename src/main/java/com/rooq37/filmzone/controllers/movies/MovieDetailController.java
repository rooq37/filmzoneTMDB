package com.rooq37.filmzone.controllers.movies;

import com.rooq37.filmzone.entities.CommentEntity;
import com.rooq37.filmzone.notifications.NotificationService;
import com.rooq37.filmzone.services.FavouriteListService;
import com.rooq37.filmzone.services.MovieService;
import com.rooq37.filmzone.services.RecommendationService;
import com.rooq37.filmzone.services.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class MovieDetailController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private ViewService viewService;
    @Autowired
    private FavouriteListService favouriteListService;

    @RequestMapping(value = "/movie/{id}", method = RequestMethod.GET)
    public String displayMovie(Principal principal,
                               Model model,
                               @PathVariable int id,
                               @RequestParam(value = "page", defaultValue = "1") Integer page,
                               @RequestParam(value = "size", defaultValue = "10") Integer size,
                               @RequestParam(value = "sort", defaultValue = "date:DESC") String sort) {

        viewService.saveViewLog(id);

        Page<CommentEntity> commentPage = movieService.getMovieComments(id, PageRequest.of(page - 1, size,
                Sort.by(Sort.Direction.fromString(sort.split(":")[1]), sort.split(":")[0])));


        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
            model.addAttribute("userRating", movieService.getMovieRatingByUser(id, principal.getName()));
            model.addAttribute("myMovies", favouriteListService.getMyMovies(id, principal.getName()));
        }
        model.addAttribute("basicImgUrl", movieService.getBasicImageUrl());
        model.addAttribute("movieDetailsDTO", movieService.getMovieDetails(id));
        model.addAttribute("commentPage", commentPage);

        return "movies/movieDetailPage";
    }


    @Autowired
    private NotificationService notificationService;
    @Autowired
    private RecommendationService recommendationService;

    @RequestMapping(value = "/rateMovie", method = RequestMethod.POST)
    public String rateMovie(Principal principal, @RequestParam(value = "rating") String rating, @RequestParam(value = "rating_movieId") String movieId){

        movieService.rateMovie(Integer.valueOf(movieId), principal.getName(), Integer.valueOf(rating));

        return "redirect:/movie/" + movieId;
    }

    @RequestMapping(value = "/addCommentToMovie", method = RequestMethod.POST)
    public String addCommentToMovie(Principal principal, @RequestParam(value = "comment_content") String content, @RequestParam(value = "comment_movieId") String movieId){

        movieService.addCommentToMovie(Integer.valueOf(movieId), principal.getName(), content);
        return "redirect:/movie/" + movieId + "#comments";
    }

    @RequestMapping(value = "/addMovieToList", method = RequestMethod.POST)
    public String addMovieToList(Principal principal,
                                 @RequestParam(value = "selectedList") String selectedList,
                                 @RequestParam(value = "action") String action,
                                 @RequestParam(value = "newListName") String newListName,
                                 @RequestParam(value = "mymovies_movieId") String movieId){

        if(action.equals("create")){
            String message = favouriteListService.createFavouriteMovieList(principal.getName(), newListName);
            if(message != null){
                notificationService.addErrorMessage(message);
                return "redirect:/movie/" + movieId;
            }else{
                favouriteListService.addMovieToFavouriteList(Integer.valueOf(movieId), principal.getName(), newListName);
                recommendationService.findSimilarMovies(principal.getName(), newListName);
            }
        }else if(action.equals("add")){
            favouriteListService.addMovieToFavouriteList(Integer.valueOf(movieId), principal.getName(), selectedList);
            recommendationService.findSimilarMovies(principal.getName(),  selectedList);
        }

        return "redirect:/movie/" + movieId + "#mymovies";
    }

    @RequestMapping(value = "/removeComment", method = RequestMethod.POST)
    public String removeComment(Principal principal,
                                @RequestParam(value = "commentId") Long commentId,
                                @RequestParam(value = "movieId") Long movieId){

        movieService.removeComment(commentId);
        return "redirect:/movie/" + movieId + "#comments";
    }

}
