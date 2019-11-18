package com.rooq37.filmzone.services;

import com.rooq37.filmzone.dtos.CharacterDTO;
import com.rooq37.filmzone.dtos.ImageDTO;
import com.rooq37.filmzone.entities.*;
import com.rooq37.filmzone.dtos.PersonDTO;
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
    private CategoryRepository categoryRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private MediaRepository mediaRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private MoviePersonRepository moviePersonRepository;

    public ImageDTO getCover(MovieEntity movieEntity){
        MediaEntity cover = mediaRepository.findByMovieAndType(movieEntity, "COVER");
        if(cover != null) {
            return new ImageDTO(movieEntity.getTitle(), PICTURES_PATH + cover.getValue(), cover.getAuthor());
        }else{
            return new ImageDTO(movieEntity.getTitle(), "../images/filmzone_default.png", "default");
        }
    }

    public List<ImageDTO> getPictures(MovieEntity movieEntity){
        List<ImageDTO> photos = new ArrayList<>();
        for(MediaEntity mm : mediaRepository.findAllByMovieEqualsAndType(movieEntity, "PICTURE"))
            photos.add(new ImageDTO(movieEntity.getTitle(), PICTURES_PATH + mm.getValue(), mm.getAuthor()));

        return photos;
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
    public void saveMoviePersonEntities(MovieEntity movie, List<PersonDTO> directors, List<PersonDTO> scenarios, List<CharacterDTO> characterDTOS){
        moviePersonRepository.deleteAllByMovie_Id(movie.getId());
        saveMoviePeople(directors, movie, null, "DIRECTOR");
        saveMoviePeople(scenarios, movie, null, "SCENARIO");
        saveMovieCharacters(characterDTOS, movie, "ACTOR");
    }

    private PersonEntity savePersonEntity(PersonDTO personDTO){
        Optional<PersonEntity> optionalPerson = personRepository.findPersonEntityByNameAndSurname(personDTO.getName(), personDTO.getSurname());
        PersonEntity personEntity;
        if(optionalPerson.isPresent()){
            personEntity = optionalPerson.get();
        }else{
            personEntity = new PersonEntity();
            personEntity.setName(personDTO.getName());
            personEntity.setSurname(personDTO.getSurname());
        }
        return personRepository.save(personEntity);
    }

    private MoviePersonEntity saveMoviePersonEntity(PersonDTO personDTO, MovieEntity movieEntity, String role, String type){
        PersonEntity personEntity = savePersonEntity(personDTO);
        MoviePersonEntity moviePersonEntity = new MoviePersonEntity();
        moviePersonEntity.setPerson(personEntity);
        moviePersonEntity.setMovie(movieEntity);
        moviePersonEntity.setRole(role);
        moviePersonEntity.setType(type);
        return moviePersonRepository.save(moviePersonEntity);
    }

    private void saveMoviePeople(List<PersonDTO> people, MovieEntity movieEntity, String role, String type){
        for(PersonDTO personDTO : people){
            saveMoviePersonEntity(personDTO, movieEntity, role, type);
        }
    }

    private void saveMovieCharacters(List<CharacterDTO> characterDTOS, MovieEntity movieEntity, String type){
        for(CharacterDTO characterDTO : characterDTOS){
            saveMoviePersonEntity(characterDTO.getActor(), movieEntity, characterDTO.getRole(), type);
        }
    }

}
