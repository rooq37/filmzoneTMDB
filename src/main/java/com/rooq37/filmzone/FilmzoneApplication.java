package com.rooq37.filmzone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class FilmzoneApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(FilmzoneApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(FilmzoneApplication.class);
    }
}
