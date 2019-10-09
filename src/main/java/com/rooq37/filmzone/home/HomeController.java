package com.rooq37.filmzone.home;

import com.rooq37.filmzone.commons.MovieListElement;
import com.rooq37.filmzone.entities.MovieEntity;
import com.rooq37.filmzone.junit.services.HelperService;
import com.rooq37.filmzone.junit.services.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private HelperService helperService;
    @Autowired
    private ViewService viewService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayHome(Model model) {

        Map<MovieListElement, Integer> mostPopularMovies = new HashMap<>();
        for(Map.Entry<MovieEntity, Integer> movie : viewService.getMostPopularMoviesLastWeek(3).entrySet())
            mostPopularMovies.put(helperService.getMovieListElement(movie.getKey()), movie.getValue());

        HomeForm homeForm = viewService.getHome();
        homeForm.setMostPopularMovies(mostPopularMovies);
        model.addAttribute("homeView", homeForm);

        return "home/homePage";
    }

}
