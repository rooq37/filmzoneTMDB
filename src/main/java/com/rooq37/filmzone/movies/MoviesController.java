package com.rooq37.filmzone.movies;

import com.rooq37.filmzone.commons.MovieListElement;
import com.rooq37.filmzone.movies.movies.MoviesFilterForm;
import com.rooq37.filmzone.services.HelperService;
import com.rooq37.filmzone.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class MoviesController {

    @Autowired
    private HelperService helperService;
    @Autowired
    private MovieService movieService;

    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public String displayMovies(Model model,
                                @RequestParam(value = "searchedMovieName", defaultValue = "") String searchedMovieName,
                                @ModelAttribute("moviesFilter") MoviesFilterForm moviesFilterForm,
                                @RequestParam(value = "page", defaultValue = "1") Integer page,
                                @RequestParam(value = "size", defaultValue = "10") Integer size,
                                @RequestParam(value = "sort", defaultValue = "1") Integer sort) {

        if(!searchedMovieName.isEmpty()) moviesFilterForm.setName(searchedMovieName);
        moviesFilterForm.setPossibleCategories(helperService.getAllCategories());
        moviesFilterForm.setPossibleCountries(helperService.getAllCountries());

        Page<MovieListElement> moviesPage = new PageImpl<>(new ArrayList<>());

        if(sort >= 3 && sort <= 6){
            switch (sort){
                case 3: moviesPage = movieService.getMovieListElements(PageRequest.of(page - 1, size, Sort.by("title").descending()), sort, moviesFilterForm);
                    break;
                case 4: moviesPage = movieService.getMovieListElements(PageRequest.of(page - 1, size, Sort.by("title").ascending()), sort, moviesFilterForm);
                    break;
                case 5: moviesPage = movieService.getMovieListElements(PageRequest.of(page - 1, size, Sort.by("year").descending()), sort, moviesFilterForm);
                    break;
                case 6: moviesPage = movieService.getMovieListElements(PageRequest.of(page - 1, size, Sort.by("year").ascending()), sort, moviesFilterForm);
                    break;
            }
        }else{
            moviesPage = movieService.getMovieListElements(PageRequest.of(page - 1, size), sort, moviesFilterForm);
        }

        model.addAttribute("moviesPage", moviesPage);

        return "movies/moviesPage";
    }

}
