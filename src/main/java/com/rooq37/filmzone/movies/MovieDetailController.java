package com.rooq37.filmzone.movies;

import com.rooq37.filmzone.entities.CommentEntity;
import com.rooq37.filmzone.services.MovieService;
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

    @RequestMapping(value = "/movie/{id}", method = RequestMethod.GET)
    public String displayMovie(Principal principal,
                               Model model,
                               @PathVariable Long id,
                               @RequestParam(value = "page", defaultValue = "1") Integer page,
                               @RequestParam(value = "size", defaultValue = "10") Integer size,
                               @RequestParam(value = "sort", defaultValue = "1") Integer sort) {

        viewService.saveViewLog(id);

        Page<CommentEntity> commentPage;
        switch(sort){
            default:
            case 1: commentPage = movieService.getMovieComments(id, PageRequest.of(page - 1, size, Sort.by("date").descending()));
            break;
            case 2: commentPage = movieService.getMovieComments(id, PageRequest.of(page - 1, size, Sort.by("date").ascending()));
            break;
            case 3: commentPage = movieService.getMovieComments(id, PageRequest.of(page - 1, size, Sort.by("rating").descending()));
            break;
            case 4: commentPage = movieService.getMovieComments(id, PageRequest.of(page - 1, size, Sort.by("rating").ascending()));
            break;
        }

        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser"))
            model.addAttribute("userRating", movieService.getMovieRatingByUser(id, principal.getName()));
        model.addAttribute("movieId", id);
        model.addAttribute("commentPage", commentPage);
        model.addAttribute("movieSummary", movieService.getMovieSummary(id));
        model.addAttribute("movieRating", movieService.getMovieRating(id));
        model.addAttribute("movieMedia", movieService.getMovieMedia(id));
        model.addAttribute("movieCast", movieService.getMovieCast(id));

        return "movies/movieDetailPage";
    }

    @RequestMapping(value = "/rateMovie", method = RequestMethod.POST)
    public String displayMovie(Principal principal, @RequestParam(value = "rating") String rating, @RequestParam(value = "rating_movieId") String movieId){

        movieService.rateMovie(Long.valueOf(movieId), principal.getName(), Integer.valueOf(rating));
        return "redirect:/movie/" + movieId;
    }

    @RequestMapping(value = "/addCommentToMovie", method = RequestMethod.POST)
    public String addCommentToMovie(Principal principal, @RequestParam(value = "comment_content") String content, @RequestParam(value = "comment_movieId") String movieId){

        movieService.addCommentToMovie(Long.valueOf(movieId), principal.getName(), content);
        return "redirect:/movie/" + movieId + "#comments";
    }

}
