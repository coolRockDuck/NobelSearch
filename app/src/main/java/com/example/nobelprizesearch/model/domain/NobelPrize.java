package com.example.nobelprizesearch.model.domain;

import androidx.annotation.NonNull;

import com.example.nobelprizesearch.model.api_response.NobelPrizeApiResponse;

import java.util.ArrayList;
import java.util.List;

public class NobelPrize {
    private final String dateAwarded;
    private final Motivation.Category category;
    private final List<Laureate> laureateList;

    public NobelPrize(String dateAwarded, Motivation.Category category, List<Laureate> laureateList) {
        this.category = category;
        this.dateAwarded = dateAwarded; // todo check for exceptions
        this.laureateList = laureateList;
    }

    public NobelPrize(NobelPrizeApiResponse response) {
        if (response == null || response.getDateAwarded() == null || response.getDateAwarded().isEmpty()) {
            throw new IllegalArgumentException("Arguments CAN'T be equal to null; response = " + response);
        }

        System.out.println("Date = " + response.getDateAwarded());
        this.dateAwarded = response.getDateAwarded(); // todo check for exceptions
        this.category = new Motivation.Category(response.getCategory());
        this.laureateList = new ArrayList<>(response.getLaureateList());
    }


    public Motivation.Category getCategory() {
        return category;
    }

    public String getDateAwarded() {
        return dateAwarded;
    }

    public List<Laureate> getLaureateList() {
        return laureateList;
    }

    @NonNull
    @Override
    public String toString() {
        return "{Year = " + dateAwarded + ", category = " + category + ", laureates = " + laureateList + "}";
    }

}
