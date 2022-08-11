package com.example.nobelprizesearch.model.domain;

import androidx.annotation.NonNull;

import java.util.Locale;

public enum Field {
    CHE, // Chemistry
    ECO, // Economy
    LIT, // Literature
    PEA, // Peace
    PHY, // Physics
    MED; // Medicine

    @NonNull
    @Override
    public String toString() {
        return name().toLowerCase(Locale.ROOT);
    }
}
