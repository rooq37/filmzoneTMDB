package com.rooq37.filmzone.controllers.mymovies;

import com.rooq37.filmzone.dtos.MovieSimpleDTO;
import com.rooq37.filmzone.notifications.NotificationService;
import com.rooq37.filmzone.services.FavouriteListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class MyMoviesController {

    private static final int PAGE_SIZE = 4;

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private FavouriteListService favouriteListService;

    @RequestMapping(value = "/myMovies", method = RequestMethod.GET)
    public String displayMyMovies(Model model,
                                  Principal principal,
                                  @RequestParam(value = "currentPage", defaultValue = "0") Integer currentPage,
                                  @RequestParam(value = "listName", defaultValue = "-1") String listName,
                                  @RequestParam(value = "action", defaultValue = "") String action){

        PagedListHolder<MovieSimpleDTO> page = favouriteListService.getFavouriteList(
                currentPage,
                PAGE_SIZE,
                principal.getName(),
                listName);

        switch (action){
            case "first": page.setPage(0); break;
            case "previous": page.previousPage(); break;
            case "next": page.nextPage(); break;
            case "last": page.setPage(page.getPageCount() - 1); break;
        }

        model.addAttribute("moviesListPage", page);
        model.addAttribute("userAllLists", favouriteListService.getUserAllLists(principal.getName()));
        model.addAttribute("selectedList", listName);
        return "mymovies/myMoviesPage.html";
    }

    @RequestMapping(value = "/createNewList", method = RequestMethod.POST)
    public String createNewList(Principal principal,
                                @RequestParam(value = "newListName") String newListName){

        String message = favouriteListService.createFavouriteMovieList(principal.getName(), newListName);
        if(message != null) notificationService.addErrorMessage(message);

        return "redirect:/myMovies";
    }

    @RequestMapping(value = "/removeFromList", method = RequestMethod.POST)
    public String removeMovieFromList(Principal principal,
                                @RequestParam(value = "listName") String listName,
                                @RequestParam(value = "movieId") String movieId){

        notificationService.addInfoMessage(favouriteListService.removeMovieFromList(principal.getName(), listName, Long.valueOf(movieId)));
        return "redirect:/myMovies?listName=" + listName;
    }

    @RequestMapping(value = "/removeList", method = RequestMethod.POST)
    public String removeList(Principal principal,
                                      @RequestParam(value = "listName") String listName){

        notificationService.addInfoMessage(favouriteListService.removeList(principal.getName(), listName));
        return "redirect:/myMovies";
    }

}
