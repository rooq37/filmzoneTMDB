package com.rooq37.filmzone.recommendations;

import info.movito.themoviedbapi.model.Genre;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.keywords.Keyword;

import java.util.*;
import java.util.stream.Collectors;

public class RecommendationEngine {

    private static final int CATEGORIES_MAX = 3;
    private static final int KEYWORDS_MAX = 2;

    private List<MovieDb> moviesFromList;
    private List<MovieDb> similarMovies;
    private Map<String, Double> mostSimilar;
    private int numberOfMovies;

    public RecommendationEngine(List<MovieDb> moviesFromList, List<MovieDb> similarMovies, int numberOfMovies){
        this.moviesFromList = moviesFromList;
        this.similarMovies = similarMovies;
        this.numberOfMovies = numberOfMovies;
        mostSimilar = new HashMap<>();
    }

    private List<String> getMostPopularSublistFromMap(Map<String, Integer> map, int maxSize){
        List<Integer> maxValues = new ArrayList<>(map.values());
        maxValues.sort((o1, o2) -> -1 * Integer.compare(o1, o2));
        maxValues = maxValues.subList(0, Math.min(maxValues.size(), maxSize));
        List<String> popular = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (maxValues.contains(entry.getValue())) {
                popular.add(entry.getKey());
            }
        }
        return popular;
    }

    private List<String> getPopularCategories(){
        Map<String, Integer> categories = new HashMap<>();
        for(MovieDb movie: moviesFromList){
            for(Genre genre : movie.getGenres()){
                Integer value = categories.get(genre.getName());
                categories.put(genre.getName(), (value == null) ? 1 : value + 1);
            }
        }
        return getMostPopularSublistFromMap(categories, CATEGORIES_MAX);
    }

    private void givePointsForCategories(){
        List<String> popularCategories = getPopularCategories();
        for(MovieDb similar : similarMovies){
            double points = 0;
            for(Genre genre : similar.getGenres()){
                if(popularCategories.contains(genre.getName()))
                    points += 1;
            }
            points /= popularCategories.size();
            Double currentPoints = mostSimilar.get(similar.getTitle());
            mostSimilar.put(similar.getTitle(), (currentPoints == null) ? points : currentPoints + points);
        }
    }

    private List<String> getPopularKeywords(){
        Map<String, Integer> keywords = new HashMap<>();
        for(MovieDb movie: moviesFromList){
            for(Keyword keyword : movie.getKeywords()){
                Integer value = keywords.get(keyword.getName());
                keywords.put(keyword.getName(), (value == null) ? 1 : value + 1);
            }
        }
        return getMostPopularSublistFromMap(keywords, KEYWORDS_MAX);
    }

    private void givePointsForKeywords(){
        List<String> popularKeywords = getPopularKeywords();
        for(MovieDb similar : similarMovies){
            double points = 0;
            for(Keyword keyword : similar.getKeywords()){
                if(popularKeywords.contains(keyword.getName()))
                    points += 1;
            }
            points /= popularKeywords.size();
            points *= 2;
            Double currentPoints = mostSimilar.get(similar.getTitle());
            mostSimilar.put(similar.getTitle(), (currentPoints == null) ? points : currentPoints + points);
        }
    }

    public void printTitles(){
        givePointsForKeywords();
        givePointsForCategories();
        mostSimilar = mostSimilar.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        for(Map.Entry<String, Double> entry : mostSimilar.entrySet()){
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

    public Set<Integer> getRecommendations(){
        givePointsForKeywords();
        givePointsForCategories();
        Set<Integer> recommendations = new HashSet<>();
        mostSimilar = mostSimilar.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        List<String> titles = moviesFromList.stream().map(MovieDb::getTitle).collect(Collectors.toList());
        for(Map.Entry<String, Double> entry : mostSimilar.entrySet()){
            if(recommendations.size() < numberOfMovies){
                for(MovieDb similar : similarMovies){
                    if(similar.getTitle().equals(entry.getKey()) && !titles.contains(similar.getTitle())){
                        recommendations.add(similar.getId());
                    }
                }
            }
        }
        return recommendations;
    }

}
