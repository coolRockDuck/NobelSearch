package com.example.nobelprizesearch.model.domain;

import androidx.annotation.NonNull;

import com.example.nobelprizesearch.model.api_response.NobelPrizeApiResponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NobelPrize implements Serializable {
    private final String dateAwarded;
    private final Motivation.Category category;
    private final List<Laureate> laureateList;

    public NobelPrize(String dateAwarded, Motivation.Category category, List<Laureate> laureateList) {
        if (dateAwarded == null || category == null || laureateList == null) {
            throw new IllegalArgumentException("Response CAN'T be equal to null");
        }

        this.category = category;
        this.dateAwarded = dateAwarded;
        this.laureateList = laureateList;
    }

    public NobelPrize(NobelPrizeApiResponse response) {
        if (response == null) {
            throw new IllegalArgumentException("Response CAN'T be equal to null");
        }

        this.dateAwarded = response.getDateAwarded();
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
