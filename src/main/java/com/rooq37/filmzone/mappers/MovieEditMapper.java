package com.rooq37.filmzone.mappers;

import com.rooq37.filmzone.dtos.CharacterDTO;
import com.rooq37.filmzone.dtos.EditMovieDTO;
import com.rooq37.filmzone.dtos.PersonDTO;
import com.rooq37.filmzone.entities.MovieEntity;
import com.rooq37.filmzone.dtos.ImageFileDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovieEditMapper extends MovieMapper {

    private EditMovieDTO editMovieDTO;

    public MovieEditMapper(MovieEntity movieEntity){
        super(movieEntity);
        editMovieDTO = new EditMovieDTO();
    }

    public EditMovieDTO getEditMovieDTO() {
        editMovieDTO.setId(movieEntity.getId());
        editMovieDTO.setTitle(movieEntity.getTitle());
        editMovieDTO.setProductionYear(movieEntity.getYear());
        editMovieDTO.setCover(getCover());
        editMovieDTO.setCategories(getCategories());
        editMovieDTO.setDescription(movieEntity.getDescription());
        editMovieDTO.setDuration(movieEntity.getDuration());
        editMovieDTO.setDirectors(getDirectors());
        editMovieDTO.setScenario(getScenarios());
        editMovieDTO.setCountries(getCountries());
        editMovieDTO.setPictures(getPictures());
        editMovieDTO.setTrailerUrl(getTrailerUrl());
        editMovieDTO.setCharacters(getCharacters());

        return editMovieDTO;
    }

    public static List<PersonDTO> getPersonList(String[] names, String[] surnames){
        if(names.length != surnames.length) return Collections.emptyList();

        List<PersonDTO> personDTOList = new ArrayList<>();
        for(int i = 0; i < names.length; i++)
            personDTOList.add(new PersonDTO(names[i], surnames[i]));

        return personDTOList;
    }

    public static List<CharacterDTO> getCharacterList(String[] actorNames, String[] actorSurnames, String[] roles){
        List<PersonDTO> actors = getPersonList(actorNames, actorSurnames);
        if(actors.size() != roles.length) return Collections.emptyList();

        List<CharacterDTO> characterDTOList = new ArrayList<>();
        for(int i = 0; i < roles.length; i++)
            characterDTOList.add(new CharacterDTO(actors.get(i), roles[i]));

        return characterDTOList;
    }

    public static List<ImageFileDTO> getImageList(MultipartFile[] files, String[] sources){
        if(files == null || sources == null) return Collections.emptyList();
        if(files.length != sources.length) return Collections.emptyList();

        List<ImageFileDTO> imageFileDTOList = new ArrayList<>();
        for(int i = 0; i < files.length; i++)
            imageFileDTOList.add(new ImageFileDTO(files[i], sources[i]));

        return imageFileDTOList;
    }

}
