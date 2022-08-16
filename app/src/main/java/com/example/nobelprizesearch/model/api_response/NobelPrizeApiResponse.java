package com.example.nobelprizesearch.model.api_response;

import androidx.annotation.NonNull;

import com.example.nobelprizesearch.model.domain.Laureate;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.stream.Collectors;

public class NobelPrizeApiResponse {
    private final String dateAwarded;
    private final MotivationApiResponse.CategoryApiResponse category;

    @SerializedName("laureates")
    private final List<LaureateApiResponse> laureateList;

    public NobelPrizeApiResponse(MotivationApiResponse.CategoryApiResponse category, String dateAwarded, List<LaureateApiResponse> laureateList) {
        this.dateAwarded = dateAwarded;
        this.category = category;
        this.laureateList = laureateList;
    }

    public MotivationApiResponse.CategoryApiResponse getCategory() {
        return category;
    }

    public String getDateAwarded() {
        return dateAwarded;
    }

    public List<LaureateApiResponse> getLaureateListApiResponse() {
        return laureateList;
    }

    public List<Laureate> getLaureateList() {
        return laureateList.stream().map(Laureate::new).collect(Collectors.toList());
    }

    @NonNull
    @Override
    public String toString() {
        return "Year = " + dateAwarded + ", category = " + category + ", laureates = " + laureateList;
    }
}
