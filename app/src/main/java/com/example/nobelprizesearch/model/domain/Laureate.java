package com.example.nobelprizesearch.model.domain;


import androidx.annotation.NonNull;

import com.example.nobelprizesearch.model.api_response.LaureateApiResponse;

public class Laureate {
    private final String id;
    private final LaureateName name;
    private final Motivation motivation;

    public Laureate(@NonNull String id, @NonNull LaureateName fullName, @NonNull Motivation motivation) {
        this.id = id;
        this.name = fullName;
        this.motivation = motivation;
    }

    public Laureate(LaureateApiResponse response) {
        if (response == null) {
            throw new IllegalArgumentException("Argument CAN'T be equal to null ");
        } else {
            this.id = response.getId();
            this.name = new LaureateName(response.getProperName());
            this.motivation = new Motivation(response.getMotivation());
        }
    }

    public String getId() {
        return id;
    }

    public LaureateName getLaureateName() {
        return name;
    }

    public Motivation getMotivation() {
        return motivation;
    }

    @NonNull
    @Override
    public String toString() {
        return "Name = " + name + " id = " + id;
    }
}
