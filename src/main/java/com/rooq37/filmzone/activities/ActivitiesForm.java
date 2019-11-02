package com.rooq37.filmzone.activities;

import java.util.ArrayList;
import java.util.List;

public class ActivitiesForm {

    private List<Activity> january = new ArrayList<>();
    private List<Activity> february = new ArrayList<>();
    private List<Activity> march = new ArrayList<>();
    private List<Activity> april = new ArrayList<>();
    private List<Activity> may = new ArrayList<>();
    private List<Activity> june = new ArrayList<>();
    private List<Activity> july = new ArrayList<>();
    private List<Activity> august = new ArrayList<>();
    private List<Activity> september = new ArrayList<>();
    private List<Activity> october = new ArrayList<>();
    private List<Activity> november = new ArrayList<>();
    private List<Activity> december = new ArrayList<>();

    public List<Activity> getMonthByNumber(int number){
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
            getMonthByNumber(i).sort(Activity.dateComparator.reversed());
    }

    public List<Activity> getJanuary() {
        return january;
    }

    public void setJanuary(List<Activity> january) {
        this.january = january;
    }

    public List<Activity> getFebruary() {
        return february;
    }

    public void setFebruary(List<Activity> february) {
        this.february = february;
    }

    public List<Activity> getMarch() {
        return march;
    }

    public void setMarch(List<Activity> march) {
        this.march = march;
    }

    public List<Activity> getApril() {
        return april;
    }

    public void setApril(List<Activity> april) {
        this.april = april;
    }

    public List<Activity> getMay() {
        return may;
    }

    public void setMay(List<Activity> may) {
        this.may = may;
    }

    public List<Activity> getJune() {
        return june;
    }

    public void setJune(List<Activity> june) {
        this.june = june;
    }

    public List<Activity> getJuly() {
        return july;
    }

    public void setJuly(List<Activity> july) {
        this.july = july;
    }

    public List<Activity> getAugust() {
        return august;
    }

    public void setAugust(List<Activity> august) {
        this.august = august;
    }

    public List<Activity> getSeptember() {
        return september;
    }

    public void setSeptember(List<Activity> september) {
        this.september = september;
    }

    public List<Activity> getOctober() {
        return october;
    }

    public void setOctober(List<Activity> october) {
        this.october = october;
    }

    public List<Activity> getNovember() {
        return november;
    }

    public void setNovember(List<Activity> november) {
        this.november = november;
    }

    public List<Activity> getDecember() {
        return december;
    }

    public void setDecember(List<Activity> december) {
        this.december = december;
    }

}
