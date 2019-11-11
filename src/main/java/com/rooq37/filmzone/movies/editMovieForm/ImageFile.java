package com.rooq37.filmzone.movies.editMovieForm;

import org.springframework.web.multipart.MultipartFile;

public class ImageFile {

    private MultipartFile multipartFile;
    private String source;

    public ImageFile(MultipartFile multipartFile, String source) {
        this.multipartFile = multipartFile;
        this.source = source;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public boolean isEmpty(){
        return (multipartFile.isEmpty() || source.isEmpty());
    }

}
