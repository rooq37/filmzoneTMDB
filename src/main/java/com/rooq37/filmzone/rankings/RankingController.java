package com.rooq37.filmzone.rankings;

import com.rooq37.filmzone.dtos.MovieSimpleDTO;
import com.rooq37.filmzone.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RankingController {

    private static final String MOST_SEARCHED_MOVIES_TITLE = "100 najczęściej wyszukiwanych filmów";
    private static final String MOST_SEARCHED_MOVIES_TYPE = "MOST_SEARCHED";

    private static final String HIGHEST_RATED_MOVIES_TITLE = "100 najwyżej ocenianych filmów";
    private static final String HIGHEST_RATED_MOVIES_TYPE = "HIGHEST_RATED";

    private static final String MOST_RATED_MOVIES_TITLE = "100 najczęściej ocenianych filmów";
    private static final String MOST_RATED_MOVIES_TYPE = "MOST_RATED";

    private static final String MOST_EXPECTED_MOVIES_TITLE = "100 najbardziej oczekiwanych filmów";
    private static final String MOST_EXPECTED_MOVIES_TYPE = "MOST_EXPECTED";

    @Autowired
    private MovieService movieService;

    @RequestMapping(value = "/ranking/most_searched", method = RequestMethod.GET)
    public String displayMostSearchedRanking(Model model,
                                             @RequestParam(value = "size", defaultValue = "10") Integer size){

        Page<MovieSimpleDTO> moviesPage = movieService
                .getMovieSimplePage(PageRequest.of(0, size, Sort.by("numberOfSearches").descending()));
        model.addAttribute("rankingPage", moviesPage);

        model.addAttribute("rankingTitle", MOST_SEARCHED_MOVIES_TITLE);
        model.addAttribute("rankingType", MOST_SEARCHED_MOVIES_TYPE);

        return "rankings/rankingPage";
    }

    @RequestMapping(value = "/ranking/highest_rated", method = RequestMethod.GET)
    public String displayHighestRatedRanking(Model model,
                                             @RequestParam(value = "size", defaultValue = "10") Integer size){

        Page<MovieSimpleDTO> moviesPage = movieService
                .getMovieSimplePage(PageRequest.of(0, size, Sort.by("averageUsersRating").descending()));
        model.addAttribute("rankingPage", moviesPage);

        model.addAttribute("rankingTitle", HIGHEST_RATED_MOVIES_TITLE);
        model.addAttribute("rankingType", HIGHEST_RATED_MOVIES_TYPE);

        return "rankings/rankingPage";
    }

    @RequestMapping(value = "/ranking/most_rated", method = RequestMethod.GET)
    public String displayMostRatedRanking(Model model,
                                             @RequestParam(value = "size", defaultValue = "10") Integer size){

        Page<MovieSimpleDTO> moviesPage = movieService
                .getMovieSimplePage(PageRequest.of(0, size, Sort.by("numberOfRatings").descending()));
        model.addAttribute("rankingPage", moviesPage);

        model.addAttribute("rankingTitle", MOST_RATED_MOVIES_TITLE);
        model.addAttribute("rankingType", MOST_RATED_MOVIES_TYPE);

        return "rankings/rankingPage";
    }

    @RequestMapping(value = "/ranking/most_expected", method = RequestMethod.GET)
    public String displayMostExpectedRanking(Model model,
                                          @RequestParam(value = "size", defaultValue = "10") Integer size){

        Page<MovieSimpleDTO> moviesPage = movieService
                .getMovieSimplePage(PageRequest.of(0, size, Sort.by("numberOfWantToWatch").descending()));
        model.addAttribute("rankingPage", moviesPage);

        model.addAttribute("rankingTitle", MOST_EXPECTED_MOVIES_TITLE);
        model.addAttribute("rankingType", MOST_EXPECTED_MOVIES_TYPE);

        return "rankings/rankingPage";
    }

}
