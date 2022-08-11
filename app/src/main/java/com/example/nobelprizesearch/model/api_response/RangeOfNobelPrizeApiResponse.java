package com.example.nobelprizesearch.model.api_response;

import androidx.annotation.NonNull;

import java.util.List;

public class RangeOfNobelPrizeApiResponse {
    private final List<NobelPrizeApiResponse> nobelPrizes;
    public RangeOfNobelPrizeApiResponse(List<NobelPrizeApiResponse> nobelPrizes) {
        this.nobelPrizes = nobelPrizes;
    }

    public List<NobelPrizeApiResponse> getNobelPrizes() {
        return nobelPrizes;
    }

    @NonNull
    @Override
    public String toString() {
        return nobelPrizes.toString();
    }
}
