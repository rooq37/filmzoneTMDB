package com.rooq37.filmzone.movies.editMovieForm;

import com.rooq37.filmzone.dtos.CharacterDTO;
import com.rooq37.filmzone.dtos.PersonDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovieEditHelper {

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

    public static List<ImageFile> getImageList(MultipartFile[] files, String[] sources){
        if(files == null || sources == null) return Collections.emptyList();
        if(files.length != sources.length) return Collections.emptyList();

        List<ImageFile> imageFileList = new ArrayList<>();
        for(int i = 0; i < files.length; i++)
            imageFileList.add(new ImageFile(files[i], sources[i]));

        return imageFileList;
    }

}
