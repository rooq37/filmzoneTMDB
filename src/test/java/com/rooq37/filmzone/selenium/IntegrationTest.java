package com.rooq37.filmzone.selenium;

import com.rooq37.filmzone.FilmzoneApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(classes = FilmzoneApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration
public class IntegrationTest {

}