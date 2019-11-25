package com.rooq37.filmzone.junit.services;

import com.rooq37.filmzone.FilmzoneApplication;
import com.rooq37.filmzone.dtos.HomeDTO;
import com.rooq37.filmzone.services.ViewService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FilmzoneApplication.class)
@Transactional
public class ViewServiceTest {

    @Autowired
    private ViewService viewService;

    @Test
    public void getHome() {
        HomeDTO homeDTO = viewService.getHome();
        assertThat(homeDTO.getNumberOfNewAccountsLastWeek()).isEqualTo(1L);
        assertThat(homeDTO.getNumberOfRatings()).isEqualTo(5L);
        assertThat(homeDTO.getNumberOfRatingsLastWeek()).isEqualTo(0L);
        assertThat(homeDTO.getNumberOfRegisteredAccounts()).isEqualTo(1L);
        assertThat(homeDTO.getNumberOfSearches()).isEqualTo(0L);
        assertThat(homeDTO.getNumberOfSearchesLastWeek()).isEqualTo(0L);
    }
}