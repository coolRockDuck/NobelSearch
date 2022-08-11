package com.example.nobelprizesearch.model.api_response;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class MotivationApiResponse {
    @SerializedName("en")
    private final String reason;

    public MotivationApiResponse(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    @NonNull
    @Override
    public String toString() {
        return reason;
    }

    public static class CategoryApiResponse {
        @SerializedName("en")
        private final String name;

        public CategoryApiResponse(@NonNull String name) {
            this.name = name;
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
