package com.rooq37.filmzone.controllers.movies;

import com.rooq37.filmzone.dtos.MovieSimpleDTO;
import com.rooq37.filmzone.dtos.MoviesFilterDTO;
import com.rooq37.filmzone.services.HelperService;
import com.rooq37.filmzone.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MoviesController {

    @Autowired
    private HelperService helperService;
    @Autowired
    private MovieService movieService;

    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public String displayMovies(Model model,
                                @RequestParam(value = "searchedMovieName", defaultValue = "") String searchedMovieName,
                                @ModelAttribute("moviesFilter") MoviesFilterDTO moviesFilterDTO,
                                @RequestParam(value = "page", defaultValue = "1") Integer page,
                                @RequestParam(value = "size", defaultValue = "10") Integer size,
                                @RequestParam(value = "sort", defaultValue = "averageUsersRating:DESC") String sort) {

        if(!searchedMovieName.isEmpty()) moviesFilterDTO.setName(searchedMovieName);
        moviesFilterDTO.setPossibleCategories(helperService.getAllCategories());
        moviesFilterDTO.setPossibleCountries(helperService.getAllCountries());

        Page<MovieSimpleDTO> moviesPage = movieService
                .getMovieSimplePage(PageRequest.of(page - 1, size, Sort.by(Sort.Direction.fromString(sort.split(":")[1]), sort.split(":")[0])), moviesFilterDTO);
        model.addAttribute("moviesPage", moviesPage);

        return "movies/moviesPage";
    }

}
