package com.rooq37.filmzone.services;

import com.rooq37.filmzone.entities.MediaEntity;
import com.rooq37.filmzone.entities.MovieEntity;
import com.rooq37.filmzone.dtos.ImageFileDTO;
import com.rooq37.filmzone.repositories.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

@Service
public class FileSaverService {

    @Autowired
    private MediaRepository mediaRepository;

    private static final String SEPARATOR = java.io.File.separator;

    private File createRootDirectory(){
        String rootPath = System.getProperty("catalina.home");
        String path = rootPath + SEPARATOR + "webapps" + SEPARATOR + "ROOT" + SEPARATOR + "movie_media";
        //String path = "C:/Projekty/Java/filmzone/src/main/resources/static/images";
        File dir = new File(path);
        if (!dir.exists()) dir.mkdirs();
        return dir;
    }

    private File createMovieDirectory(MovieEntity movie){
        File root = createRootDirectory();
        File movieDir = new File(root.getAbsolutePath() + SEPARATOR + "movie_" + movie.getId());
        if(!movieDir.exists()) movieDir.mkdirs();
        return movieDir;
    }

    public MediaEntity saveMediaEntity(ImageFileDTO image, MovieEntity movie, String type) throws IOException {
        if(image.getMultipartFile().isEmpty()) return new MediaEntity();

        File movieDir = createMovieDirectory(movie);

        byte[] bytes = image.getMultipartFile().getBytes();
        String timestamp = String.valueOf(new Date().getTime());
        String format = (image.getMultipartFile().getContentType().equals("image/jpeg")) ? ".jpg" : ".bmp";
        String filename = timestamp + "_movie_" + movie.getId() + format;
        File serverFile = new File(movieDir.getPath() + "/" + filename);
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
        stream.write(bytes);
        stream.close();

        MediaEntity media = new MediaEntity();
        media.setAuthor(image.getSource());
        media.setDate(new Date());
        media.setType(type);
        media.setValue("movie_" + movie.getId() + "/" + filename);
        media.setMovie(movie);

        return mediaRepository.save(media);
    }

    @Transactional
    public void removeMediaEntity(String path){
        path = path.replaceAll("../images/", "");
        File serverFile = new File(createRootDirectory() + SEPARATOR + path);
        serverFile.delete();
        mediaRepository.deleteByValue(path);
    }

}
