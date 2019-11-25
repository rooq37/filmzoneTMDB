package com.rooq37.filmzone.controllers.rankings;

import com.rooq37.filmzone.services.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private RankingService rankingService;

    @RequestMapping(value = "/ranking/most_searched", method = RequestMethod.GET)
    public String displayMostSearchedRanking(Model model,
                                             @RequestParam(value = "size", defaultValue = "10") Integer size){


        model.addAttribute("ranking", rankingService.getMostSearchedMovies(size));
        model.addAttribute("rankingPageSize", size);
        model.addAttribute("rankingTitle", MOST_SEARCHED_MOVIES_TITLE);
        model.addAttribute("rankingType", MOST_SEARCHED_MOVIES_TYPE);

        return "rankings/rankingPage";
    }

    @RequestMapping(value = "/ranking/highest_rated", method = RequestMethod.GET)
    public String displayHighestRatedRanking(Model model,
                                             @RequestParam(value = "size", defaultValue = "10") Integer size){

        model.addAttribute("ranking", rankingService.getHighestRatedMovies(size));
        model.addAttribute("rankingPageSize", size);
        model.addAttribute("rankingTitle", HIGHEST_RATED_MOVIES_TITLE);
        model.addAttribute("rankingType", HIGHEST_RATED_MOVIES_TYPE);

        return "rankings/rankingPage";
    }

    @RequestMapping(value = "/ranking/most_rated", method = RequestMethod.GET)
    public String displayMostRatedRanking(Model model,
                                             @RequestParam(value = "size", defaultValue = "10") Integer size){

        model.addAttribute("ranking", rankingService.getMostRatedMovies(size));
        model.addAttribute("rankingPageSize", size);
        model.addAttribute("rankingTitle", MOST_RATED_MOVIES_TITLE);
        model.addAttribute("rankingType", MOST_RATED_MOVIES_TYPE);

        return "rankings/rankingPage";
    }

}
