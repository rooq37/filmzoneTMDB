package com.rooq37.filmzone.controllers.movies;

import com.rooq37.filmzone.dtos.MoviesFilterDTO;
import com.rooq37.filmzone.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MoviesController {


    @Autowired
    private MovieService movieService;

    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public String displayMovies(Model model,
                                @RequestParam(value = "searchedMovieName", defaultValue = "") String searchedMovieName,
                                @ModelAttribute("moviesFilter") MoviesFilterDTO moviesFilterDTO,
                                @RequestParam(value = "page", defaultValue = "1") Integer page) {

        moviesFilterDTO.setPossibleCategories(movieService.getAllPossibleCategories());
        model.addAttribute("basicImgUrl", movieService.getBasicImageUrl());
        if(searchedMovieName.isEmpty()){
            model.addAttribute("moviesPage", movieService.getMovieResultsPage(moviesFilterDTO, page));
            model.addAttribute("movieName", "");
        }else{
            model.addAttribute("moviesPage", movieService.getMovieResultsPageByName(searchedMovieName, page));
            model.addAttribute("movieName", searchedMovieName);
        }
        return "movies/moviesPage";
    }

}
