package com.rooq37.filmzone.movies.editMovieForm;

import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovieEditHelper {

    public static List<Person> getPersonList(String[] names, String[] surnames){
        if(names.length != surnames.length) return Collections.emptyList();

        List<Person> personList = new ArrayList<>();
        for(int i = 0; i < names.length; i++)
            personList.add(new Person(names[i], surnames[i]));

        return personList;
    }

    public static List<Character> getCharacterList(String[] actorNames, String[] actorSurnames, String[] roles){
        List<Person> actors = getPersonList(actorNames, actorSurnames);
        if(actors.size() != roles.length) return Collections.emptyList();

        List<Character> characterList = new ArrayList<>();
        for(int i = 0; i < roles.length; i++)
            characterList.add(new Character(actors.get(i), roles[i]));

        return characterList;
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
