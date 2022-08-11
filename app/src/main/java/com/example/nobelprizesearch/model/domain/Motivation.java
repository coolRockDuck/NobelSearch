package com.example.nobelprizesearch.model.domain;

import androidx.annotation.NonNull;

import com.example.nobelprizesearch.model.api_response.MotivationApiResponse;
import com.google.gson.annotations.SerializedName;

public class Motivation {
    private final String reason;

    public Motivation(String reason) {
        this.reason = reason;
    }

    public Motivation(MotivationApiResponse motivation) {
        if (motivation == null || motivation.getReason() == null || motivation.getReason().isEmpty()) {
            throw new IllegalArgumentException("");
        }

        reason = motivation.getReason();
    }

    public String getReason() {
        return reason;
    }

    public static class Category {
        private final String name;

        public Category(@NonNull String name) {
            this.name = name;
        }

        public Category(MotivationApiResponse.CategoryApiResponse response) {
            if (response == null || response.getCategoryFullName() == null) {
                throw new IllegalArgumentException("");
            }

            name = response.getCategoryFullName();
        }

        public String getCategoryFullName() {
            return name;
        }

        @NonNull
        @Override
        public String toString() {
            return name;
        }
    }
}
