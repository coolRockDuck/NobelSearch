package com.example.nobelprizesearch.model.api_response;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class LaureateNameApiResponse {
    @SerializedName("en")
    private final String name;

    public LaureateNameApiResponse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static boolean isValid(LaureateNameApiResponse response) {
        return response != null && response.getName() != null && !response.getName().isEmpty();
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}