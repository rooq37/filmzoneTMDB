package com.rooq37.filmzone.movies;

import com.rooq37.filmzone.entities.CommentEntity;
import com.rooq37.filmzone.junit.services.MovieService;
import com.rooq37.filmzone.junit.services.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MovieDetailController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private ViewService viewService;

    @RequestMapping(value = "/movie/{id}", method = RequestMethod.GET)
    public String displayMovie(Model model,
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

        model.addAttribute("movieId", id);
        model.addAttribute("commentPage", commentPage);
        model.addAttribute("movieSummary", movieService.getMovieSummary(id));
        model.addAttribute("movieRating", movieService.getMovieRating(id));
        model.addAttribute("movieMedia", movieService.getMovieMedia(id));
        model.addAttribute("movieCast", movieService.getMovieCast(id));

        return "movies/movieDetailPage";
    }

}
