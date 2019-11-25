package com.rooq37.filmzone.dtos;

import java.util.ArrayList;
import java.util.List;

public class ActivitiesDTO {

    private List<ActivityDTO> january = new ArrayList<>();
    private List<ActivityDTO> february = new ArrayList<>();
    private List<ActivityDTO> march = new ArrayList<>();
    private List<ActivityDTO> april = new ArrayList<>();
    private List<ActivityDTO> may = new ArrayList<>();
    private List<ActivityDTO> june = new ArrayList<>();
    private List<ActivityDTO> july = new ArrayList<>();
    private List<ActivityDTO> august = new ArrayList<>();
    private List<ActivityDTO> september = new ArrayList<>();
    private List<ActivityDTO> october = new ArrayList<>();
    private List<ActivityDTO> november = new ArrayList<>();
    private List<ActivityDTO> december = new ArrayList<>();

    public List<ActivityDTO> getMonthByNumber(int number){
        switch (number){
            case 1: return january;
            case 2: return february;
            case 3: return march;
            case 4: return april;
            case 5: return may;
            case 6: return june;
            case 7: return july;
            case 8: return august;
            case 9: return september;
            case 10: return october;
            case 11: return november;
            case 12: return december;
        }
        return new ArrayList<>();
    }

    public void sortByDate(){
        for(int i = 1; i <= 12; i++)
            getMonthByNumber(i).sort(ActivityDTO.dateComparator.reversed());
    }

    public List<ActivityDTO> getJanuary() {
        return january;
    }

    public void setJanuary(List<ActivityDTO> january) {
        this.january = january;
    }

    public List<ActivityDTO> getFebruary() {
        return february;
    }

    public void setFebruary(List<ActivityDTO> february) {
        this.february = february;
    }

    public List<ActivityDTO> getMarch() {
        return march;
    }

    public void setMarch(List<ActivityDTO> march) {
        this.march = march;
    }

    public List<ActivityDTO> getApril() {
        return april;
    }

    public void setApril(List<ActivityDTO> april) {
        this.april = april;
    }

    public List<ActivityDTO> getMay() {
        return may;
    }

    public void setMay(List<ActivityDTO> may) {
        this.may = may;
    }

    public List<ActivityDTO> getJune() {
        return june;
    }

    public void setJune(List<ActivityDTO> june) {
        this.june = june;
    }

    public List<ActivityDTO> getJuly() {
        return july;
    }

    public void setJuly(List<ActivityDTO> july) {
        this.july = july;
    }

    public List<ActivityDTO> getAugust() {
        return august;
    }

    public void setAugust(List<ActivityDTO> august) {
        this.august = august;
    }

    public List<ActivityDTO> getSeptember() {
        return september;
    }

    public void setSeptember(List<ActivityDTO> september) {
        this.september = september;
    }

    public List<ActivityDTO> getOctober() {
        return october;
    }

    public void setOctober(List<ActivityDTO> october) {
        this.october = october;
    }

    public List<ActivityDTO> getNovember() {
        return november;
    }

    public void setNovember(List<ActivityDTO> november) {
        this.november = november;
    }

    public List<ActivityDTO> getDecember() {
        return december;
    }

    public void setDecember(List<ActivityDTO> december) {
        this.december = december;
    }

}
