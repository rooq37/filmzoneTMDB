package com.rooq37.filmzone.services;

import com.rooq37.filmzone.commons.Image;
import com.rooq37.filmzone.commons.MovieListElement;
import com.rooq37.filmzone.entities.*;
import com.rooq37.filmzone.movies.editMovieForm.Character;
import com.rooq37.filmzone.movies.editMovieForm.Person;
import com.rooq37.filmzone.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HelperService {

    private static final String SEPARATOR = File.separator;

    private static final String PICTURES_PATH = "../images/";
    //private static final String PICTURES_PATH = System.getProperty("catalina.home") + SEPARATOR + "webapps" + SEPARATOR + "ROOT" + SEPARATOR + "movie_media" + SEPARATOR;


    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ViewRepository viewRepository;
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private MediaRepository mediaRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private MoviePersonRepository moviePersonRepository;

    public List<MovieListElement> getAllMovieListElements(){
        List<MovieListElement> movieList = new ArrayList<>();
        List<MovieEntity> movies = movieRepository.findAll();

        for(MovieEntity movie : movies)
            movieList.add(getMovieListElement(movie));

        return movieList;
    }

    public MovieListElement getMovieListElement(MovieEntity movie){
        MovieListElement mle = new MovieListElement();

        mle.setId(movie.getId());
        mle.setTitle(movie.getTitle());
        mle.setCover(getCover(movie));
        mle.setCategories(getCategories(movie));
        mle.setCountries(getCountries(movie));
        mle.setDescription(movie.getDescription());
        mle.setYear(movie.getYear());
        mle.setAvgUsersRating(countAverageRating(movie));
        mle.setNumberOfPeopleWhoWatched(ratingRepository.countByMovieAndValueGreaterThan(movie, 0));
        mle.setNumberOfPeopleWhoWantToWatch(ratingRepository.countByMovieAndValueIs(movie, 0));
        mle.setNumberOfSearches(viewRepository.countByMovie(movie));

        return mle;
    }

    public double countAverageRating(MovieEntity movieEntity){
        List<Integer> ratings = ratingRepository.findAllByMovie(movieEntity).stream().map(RatingEntity::getValue).collect(Collectors.toList());
        if(ratings.isEmpty()) return 0;
        return ratings.stream().mapToInt(Integer::intValue).average().getAsDouble();
    }

    public Image getCover(MovieEntity movieEntity){
        List<MediaEntity> mediaEntityList = mediaRepository.findAllByMovieEqualsAndType(movieEntity, "COVER");
        MediaEntity cover = !mediaEntityList.isEmpty() ? mediaEntityList.get(0) : new MediaEntity();
        return new Image(movieEntity.getTitle(), PICTURES_PATH + cover.getValue(), cover.getAuthor());
    }

    public List<Image> getPictures(MovieEntity movieEntity){
        List<Image> photos = new ArrayList<>();
        for(MediaEntity mm : mediaRepository.findAllByMovieEqualsAndType(movieEntity, "PICTURE"))
            photos.add(new Image(movieEntity.getTitle(), PICTURES_PATH + mm.getValue(), mm.getAuthor()));

        return photos;
    }

    public List<String> getCategories(MovieEntity movieEntity){
        return movieEntity.getCategories().stream().
                map(CategoryEntity::getName).collect(Collectors.toList());
    }

    public List<String> getCountries(MovieEntity movieEntity){
        return movieEntity.getCountries().stream().
                map(CountryEntity::getName).collect(Collectors.toList());
    }

    public List<String> getAllCategories(){
        return categoryRepository.findAll().stream().map(CategoryEntity::getName).collect(Collectors.toList());
    }

    public List<String> getAllCountries(){
        return countryRepository.findAll().stream().map(CountryEntity::getName).collect(Collectors.toList());
    }

    public void saveCategoryEntities(MovieEntity movie, List<String> categories){
        for(CategoryEntity category : categoryRepository.findAll()){
            if(categories.contains(category.getName())){
                category.getMovies().add(movie);
            }else{
                category.getMovies().remove(movie);
            }
            categoryRepository.save(category);
        }
    }

    public void saveCountryEntities(MovieEntity movie, List<String> countries){
        for(String country : countries){
            Optional<CountryEntity> optionalCountry = countryRepository.findCountryEntityByName(country);
            CountryEntity countryEntity;
            if(optionalCountry.isPresent()){
                countryEntity = optionalCountry.get();
            }else{
                countryEntity = new CountryEntity();
                countryEntity.setName(country);
            }
            countryEntity.getMovies().add(movie);
            countryRepository.save(countryEntity);
        }
        for(CountryEntity country : countryRepository.findAll()){
            if(!countries.contains(country.getName())){
                country.getMovies().remove(movie);
                countryRepository.save(country);
            }
        }
    }

    @Transactional
    public void saveMoviePersonEntities(MovieEntity movie, List<Person> directors, List<Person> scenarios, List<Character> characters){
        moviePersonRepository.deleteAllByMovie_Id(movie.getId());
        saveMoviePeople(directors, movie, null, "DIRECTOR");
        saveMoviePeople(scenarios, movie, null, "SCENARIO");
        saveMovieCharacters(characters, movie, "ACTOR");
    }

    private PersonEntity savePersonEntity(Person person){
        Optional<PersonEntity> optionalPerson = personRepository.findPersonEntityByNameAndSurname(person.getName(), person.getSurname());
        PersonEntity personEntity;
        if(optionalPerson.isPresent()){
            personEntity = optionalPerson.get();
        }else{
            personEntity = new PersonEntity();
            personEntity.setName(person.getName());
            personEntity.setSurname(person.getSurname());
        }
        return personRepository.save(personEntity);
    }

    private MoviePersonEntity saveMoviePersonEntity(Person person, MovieEntity movieEntity, String role, String type){
        PersonEntity personEntity = savePersonEntity(person);
        MoviePersonEntity moviePersonEntity = new MoviePersonEntity();
        moviePersonEntity.setPerson(personEntity);
        moviePersonEntity.setMovie(movieEntity);
        moviePersonEntity.setRole(role);
        moviePersonEntity.setType(type);
        return moviePersonRepository.save(moviePersonEntity);
    }

    private void saveMoviePeople(List<Person> people, MovieEntity movieEntity, String role, String type){
        for(Person person : people){
            saveMoviePersonEntity(person, movieEntity, role, type);
        }
    }

    private void saveMovieCharacters(List<Character> characters, MovieEntity movieEntity, String type){
        for(Character character : characters){
            saveMoviePersonEntity(character.getActor(), movieEntity, character.getRole(), type);
        }
    }

}
