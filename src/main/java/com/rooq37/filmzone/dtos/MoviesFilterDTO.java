package com.rooq37.filmzone.dtos;

import com.rooq37.filmzone.entities.CategoryEntity;
import com.rooq37.filmzone.entities.CountryEntity;
import com.rooq37.filmzone.entities.MovieEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class MoviesFilterDTO {

    private static final String DELIMITER = ", ";

    private String name = "";
    private int minYear = 1900;
    private int maxYear = 2020;
    private String selectedCategories;
    private List<String> possibleCategories;
    private String selectedCountries;
    private List<String> possibleCountries;
    private int minRate = 0;
    private int maxRate = 10;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinYear() {
        return minYear;
    }

    public void setMinYear(int minYear) {
        this.minYear = minYear;
    }

    public int getMaxYear() {
        return maxYear;
    }

    public void setMaxYear(int maxYear) {
        this.maxYear = maxYear;
    }

    public String getSelectedCategories() {
        return selectedCategories;
    }

    private List<String> getSelectedCategoriesAsList(){
        if(selectedCategories == null || selectedCategories.isEmpty()) return new ArrayList<>();
        return new ArrayList<>(Arrays.asList(selectedCategories.split(DELIMITER)));
    }

    public void setSelectedCategories(String selectedCategories) {
        this.selectedCategories = selectedCategories;
    }

    public List<String> getPossibleCategories() {
        return possibleCategories;
    }

    public void setPossibleCategories(List<String> possibleCategories) {
        this.possibleCategories = possibleCategories;
    }

    public String getSelectedCountries() {
        return selectedCountries;
    }

    private List<String> getSelectedCountriesAsList(){
        if(selectedCountries == null || selectedCountries.isEmpty()) return new ArrayList<>();
        return new ArrayList<>(Arrays.asList(selectedCountries.split(DELIMITER)));
    }

    public void setSelectedCountries(String selectedCountries) {
        this.selectedCountries = selectedCountries;
    }

    public List<String> getPossibleCountries() {
        return possibleCountries;
    }

    public void setPossibleCountries(List<String> possibleCountries) {
        this.possibleCountries = possibleCountries;
    }

    public int getMinRate() {
        return minRate;
    }

    public void setMinRate(int minRate) {
        this.minRate = minRate;
    }

    public int getMaxRate() {
        return maxRate;
    }

    public void setMaxRate(int maxRate) {
        this.maxRate = maxRate;
    }

    public boolean checkIfCategoriesMatchFilter(List<String> movieCategories){
        for(String selectedCategory : getSelectedCategoriesAsList()){
            if(!movieCategories.contains(selectedCategory)) return false;
        }
        return true;
    }

    public boolean checkIfCountriesMatchFilter(List<String> movieCountries){
        for(String selectedCountry : getSelectedCountriesAsList()){
            if(!movieCountries.contains(selectedCountry)) return false;
        }
        return true;
    }

    private Predicate getCategoriesPredicate(Root<MovieEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder){
        Predicate[] predicates = new Predicate[getSelectedCategoriesAsList().size()];
        for(int i = 0; i < getSelectedCategoriesAsList().size(); i++){
            Subquery<CategoryEntity> categorySubquery = criteriaQuery.subquery(CategoryEntity.class);
            Root<CategoryEntity> categoryRoot = categorySubquery.from(CategoryEntity.class);
            Expression<Collection<MovieEntity>> moviesCollection = categoryRoot.get("movies");

            categorySubquery
                    .select(categoryRoot)
                    .where(criteriaBuilder.and(criteriaBuilder.equal(categoryRoot.get("name"), getSelectedCategoriesAsList().get(i)), criteriaBuilder.isMember(root, moviesCollection)));
            predicates[i] = criteriaBuilder.exists(categorySubquery);
        }
        return criteriaBuilder.and(predicates);
    }

    private Predicate getCountriesPredicate(Root<MovieEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder){
        Predicate[] predicates = new Predicate[getSelectedCountriesAsList().size()];
        for(int i = 0; i < getSelectedCountriesAsList().size(); i++){
            Subquery<CountryEntity> countrySubquery = criteriaQuery.subquery(CountryEntity.class);
            Root<CountryEntity> countryRoot = countrySubquery.from(CountryEntity.class);
            Expression<Collection<MovieEntity>> moviesCollection = countryRoot.get("movies");

            countrySubquery
                    .select(countryRoot)
                    .where(criteriaBuilder.and(criteriaBuilder.equal(countryRoot.get("name"), getSelectedCountriesAsList().get(i)), criteriaBuilder.isMember(root, moviesCollection)));
            predicates[i] = criteriaBuilder.exists(countrySubquery);
        }
        return criteriaBuilder.and(predicates);
    }

    public Specification<MovieEntity> movieMatchesFilter() {
        return (Specification<MovieEntity>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.between(root.get("year"), minYear, maxYear),
                criteriaBuilder.or(
                        criteriaBuilder.between(root.get("averageUsersRating"), minRate, maxRate),
                        criteriaBuilder.isNull(root.get("averageUsersRating"))
                ),
                getCategoriesPredicate(root, criteriaQuery, criteriaBuilder),
                getCountriesPredicate(root, criteriaQuery, criteriaBuilder),
                criteriaBuilder.or(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(
                                        root.get(
                                                root.getModel().getDeclaredSingularAttribute("title", String.class)
                                        )
                                ), criteriaBuilder.lower(criteriaBuilder.literal("%" + name + "%")
                                )
                        ),
                        criteriaBuilder.equal(criteriaBuilder.literal(name), "")
                )
        );
    }

}
