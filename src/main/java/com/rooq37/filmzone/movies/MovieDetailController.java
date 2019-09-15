package com.rooq37.filmzone.movies;

import com.rooq37.filmzone.commons.CastPair;
import com.rooq37.filmzone.commons.Comment;
import com.rooq37.filmzone.commons.Image;
import com.rooq37.filmzone.movies.movieDetail.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class MovieDetailController {

    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/movie/{id}", method = RequestMethod.GET)
    public String displayMovie(Model model,
                               @PathVariable Long id,
                               @RequestParam("page") Optional<Integer> page,
                               @RequestParam("size") Optional<Integer> size,
                               @RequestParam("sort") Optional<Integer> sort) {

        System.out.println(sort);

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Page<Comment> commentPage;
        switch(sort.orElse(1)){
            default:
            case 1: commentPage = commentService.findPaginated(PageRequest.of(currentPage - 1, pageSize, Sort.by("date").descending()));
            break;
            case 2: commentPage = commentService.findPaginated(PageRequest.of(currentPage - 1, pageSize, Sort.by("date").ascending()));
            break;
            case 3: commentPage = commentService.findPaginated(PageRequest.of(currentPage - 1, pageSize, Sort.by("rating").descending()));
            break;
            case 4: commentPage = commentService.findPaginated(PageRequest.of(currentPage - 1, pageSize, Sort.by("rating").ascending()));
            break;
        }

        model.addAttribute("commentPage", commentPage);

        int totalPages = commentPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("movieSummary", getMovieSummary());
        model.addAttribute("movieRating", getMovieRating());
        model.addAttribute("movieMedia", getMovieMedia());
        model.addAttribute("movieCast", getMovieCast());

        return "movies/movieDetailPage";
    }

    private MovieSummary getMovieSummary(){
        MovieSummary movieSummary = new MovieSummary();
        movieSummary.setTitle("The Godfather");

        Image cover = new Image("The Godfather", "../images/the_godfather.jpg", "Paramount pictures");
        movieSummary.setCover(cover);

        List<String> categories = new ArrayList<>();
        categories.add("drama");
        categories.add("gangster");
        movieSummary.setCategories(categories);

        movieSummary.setDescription("The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.");

        movieSummary.setDuration(175);

        movieSummary.setDirector("Francis Ford Coppola");

        movieSummary.setScenario("Mario Puzo, Francis Ford Coppola");

        movieSummary.setCountry("USA");

        movieSummary.setAvgUsersRating("9,2");

        return movieSummary;
    }

    private MovieRating getMovieRating(){
        MovieRating movieRating = new MovieRating();
        movieRating.setAvg("9,2");
        movieRating.setCast("9,5");
        movieRating.setStory("9,2");
        movieRating.setMusic("8,9");
        movieRating.setNumberOfPeopleWhoWatched("504 471");
        movieRating.setNumberOfPeopleWhoWantToWatch("92 329");
        return movieRating;
    }

    private MovieMedia getMovieMedia(){
        MovieMedia movieMedia = new MovieMedia();

        List<Image> photos = new ArrayList<>();
        photos.add(new Image("Godfather 1", "../images/godfather/godfather1.jpg", "Paramount Pictures"));
        photos.add(new Image("Godfather 2", "../images/godfather/godfather2.jpg", "Paramount Pictures"));
        photos.add(new Image("Godfather 3", "../images/godfather/godfather3.jpg", "Paramount Pictures"));
        photos.add(new Image("Godfather 4", "../images/godfather/godfather4.jpg", "Paramount Pictures"));
        photos.add(new Image("Godfather 5", "../images/godfather/godfather5.jpg", "Paramount Pictures"));
        movieMedia.setPhotos(photos);
        movieMedia.setTrailerLink("https://www.youtube.com/embed/sY1S34973zA");
        return movieMedia;
    }

    private MovieCast getMovieCast(){
        MovieCast movieCast = new MovieCast();

        List<CastPair> cast = new ArrayList<>();
        cast.add(new CastPair("Marlon Brando", "Don Vito Corleone"));
        cast.add(new CastPair("Al Pacino", "Michael Corleone"));
        cast.add(new CastPair("James Caan", "Sonny Corleone"));
        cast.add(new CastPair("Richard S. Castellano", "Peter Clemenza"));
        cast.add(new CastPair("Robert Duvall", "Tom Hagen"));
        movieCast.setCast(cast);
        return movieCast;
    }

    public static MovieComments getMovieComments(int number){
        MovieComments movieComments = new MovieComments();

        List<Comment> comments = new ArrayList<>();
        Date date = new Date();
        for(int i = 0; i < number; i++){
            Comment comment = new Comment();
            comment.setAuthor("author" + i);
            comment.setContent("content" + i);
            Calendar cal = Calendar.getInstance();
            comment.setDate(date);
            cal.setTime(date);
            cal.add(Calendar.DATE, 1);
            date = cal.getTime();
            Random random = new Random();
            comment.setRating(random.nextInt()%20);
            comments.add(comment);
        }
        movieComments.setComments(comments);
        return movieComments;
    }

}
