package com.rooq37.filmzone.rankings;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RankingController {

    @RequestMapping(value = "/ranking", method = RequestMethod.GET)
    public String displayRanking() {

        return "rankings/rankingPage";
    }

}
