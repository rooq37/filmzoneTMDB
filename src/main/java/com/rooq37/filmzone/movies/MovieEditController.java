package com.rooq37.filmzone.movies;

import com.rooq37.filmzone.movies.editMovieForm.EditMovieForm;
import com.rooq37.filmzone.movies.editMovieForm.ImageFile;
import com.rooq37.filmzone.movies.editMovieForm.MovieEditHelper;
import com.rooq37.filmzone.services.HelperService;
import com.rooq37.filmzone.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Controller
public class MovieEditController {

    @Autowired
    private HelperService helperService;
    @Autowired
    private MovieService movieService;

    @RequestMapping(value = "/addMovie", method = RequestMethod.GET)
    public String displayMovieAddition(Model model) {

        model.addAttribute("movieForm", new EditMovieForm());
        model.addAttribute("allCategories", helperService.getAllCategories());
        return "movies/movieEditPage";
    }

    @RequestMapping(value = "/saveMovie", method = RequestMethod.POST)
    public String saveMovie(@RequestParam(value = "cover_file", required = false) MultipartFile coverFile,
                            @RequestParam(value = "cover_author", required = false) String coverAuthor,
                            @RequestParam(value = "selectedCategory") String[] categories,
                            @RequestParam(value = "movie_directors_name") String[] movieDirectorsName,
                            @RequestParam(value = "movie_directors_surname") String[] movieDirectorsSurname,
                            @RequestParam(value = "movie_scenario_name") String[] movieScenarioName,
                            @RequestParam(value = "movie_scenario_surname") String[] movieScenarioSurname,
                            @RequestParam(value = "movie_country") String[] movieCountry,
                            @RequestParam(value = "picture_file", required = false) MultipartFile[] pictureFiles,
                            @RequestParam(value = "picture_author", required = false) String[] pictureAuthors,
                            @RequestParam(value = "movie_cast_actor_name") String[] movieCastActorName,
                            @RequestParam(value = "movie_cast_actor_surname") String[] movieCastActorSurname,
                            @RequestParam(value = "movie_cast_actor_role") String[] movieCastActorRole,
                            @ModelAttribute("movieForm") EditMovieForm movieForm){

        movieForm.setCategories(Arrays.asList(categories));
        movieForm.setDirectors(MovieEditHelper.getPersonList(movieDirectorsName, movieDirectorsSurname));
        movieForm.setScenario(MovieEditHelper.getPersonList(movieScenarioName, movieScenarioSurname));
        movieForm.setCountries(Arrays.asList(movieCountry));
        movieForm.setCharacters(MovieEditHelper.getCharacterList(movieCastActorName, movieCastActorSurname, movieCastActorRole));
        ImageFile cover = new ImageFile(coverFile, coverAuthor);
        List<ImageFile> pictures = MovieEditHelper.getImageList(pictureFiles, pictureAuthors);
        movieService.saveMovie(movieForm, cover, pictures);

        return "redirect:/addMovie";
    }

}
