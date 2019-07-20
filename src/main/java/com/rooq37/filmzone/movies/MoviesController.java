package com.rooq37.filmzone.movies;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MoviesController {

    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public String displayMovies() {

        return "movies/moviesPage";
    }

}
