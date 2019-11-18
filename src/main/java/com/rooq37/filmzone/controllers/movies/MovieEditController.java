package com.rooq37.filmzone.controllers.movies;

import com.rooq37.filmzone.entities.MovieEntity;
import com.rooq37.filmzone.dtos.EditMovieDTO;
import com.rooq37.filmzone.mappers.MovieEditMapper;
import com.rooq37.filmzone.dtos.ImageFileDTO;
import com.rooq37.filmzone.services.HelperService;
import com.rooq37.filmzone.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
public class MovieEditController {

    @Autowired
    private HelperService helperService;
    @Autowired
    private MovieService movieService;

    @RequestMapping(value = "/addMovie", method = RequestMethod.GET)
    public String displayMovieAddition(Model model) {

        model.addAttribute("movieForm", new EditMovieDTO());
        model.addAttribute("allCategories", helperService.getAllCategories());
        return "movies/movieEditPage";
    }

    @RequestMapping(value = "/editMovie/{id}", method = RequestMethod.GET)
    public String editMovie(@PathVariable Long id,
                            Model model){

        model.addAttribute("movieForm", movieService.getEditMovieForm(id));
        model.addAttribute("allCategories", helperService.getAllCategories());
        return "movies/movieEditPage";
    }

    @RequestMapping(value = "/saveMovie", method = RequestMethod.POST)
    public String saveMovie(@RequestParam(value = "cover_file", required = false) MultipartFile coverFile,
                            @RequestParam(value = "cover_author", required = false) String coverAuthor,
                            @RequestParam(value = "selectedCategory", required = false) String[] categories,
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
                            @RequestParam(value = "old_picture", required = false) String[] oldPictures,
                            @ModelAttribute("movieForm") EditMovieDTO movieForm){

        movieForm.setCategories( (categories != null) ? Arrays.asList(categories) : Collections.emptyList() );
        movieForm.setDirectors(MovieEditMapper.getPersonList(movieDirectorsName, movieDirectorsSurname));
        movieForm.setScenario(MovieEditMapper.getPersonList(movieScenarioName, movieScenarioSurname));
        movieForm.setCountries(Arrays.asList(movieCountry));
        movieForm.setCharacters(MovieEditMapper.getCharacterList(movieCastActorName, movieCastActorSurname, movieCastActorRole));
        ImageFileDTO cover = new ImageFileDTO(coverFile, coverAuthor);
        List<ImageFileDTO> pictures = MovieEditMapper.getImageList(pictureFiles, pictureAuthors);
        MovieEntity movieUnderEdition = movieService.saveMovie(movieForm, cover, pictures,
                (oldPictures != null) ? Arrays.asList(oldPictures) : Collections.emptyList());

        return "redirect:/editMovie/" + movieUnderEdition.getId();
    }

}
