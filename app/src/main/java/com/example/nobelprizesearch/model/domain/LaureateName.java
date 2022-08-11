package com.example.nobelprizesearch.model.domain;

import androidx.annotation.NonNull;

import com.example.nobelprizesearch.model.api_response.LaureateNameApiResponse;
import com.google.gson.annotations.SerializedName;

public class LaureateName {
    private final String name;

    public LaureateName(@NonNull String name) {
        this.name = name;
    }

    public LaureateName(LaureateNameApiResponse response) {
        if (!LaureateNameApiResponse.isValid(response)) {
            throw new IllegalArgumentException(response.toString());
        }

        name = response.getName();
    }

    public String getName() {
        return name;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}