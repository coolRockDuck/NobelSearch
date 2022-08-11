package com.example.nobelprizesearch.data;

import com.example.nobelprizesearch.model.domain.Field;
import com.example.nobelprizesearch.model.domain.NobelPrize;

import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.Single;

public class NobelPrizeClient {
    private final NobelPrizeService service;

    public NobelPrizeClient(NobelPrizeService service) {
        this.service = service;
    }

    public Single<List<NobelPrize>> fetchNobelPrizeForYear(String year, Field field) {
        return service.fetchNobelPrizeForYear(year, field).map(
                (list) -> list.stream().map(NobelPrize::new).collect(Collectors.toList())
        );
    }
    public Single<List<NobelPrize>> fetchNobelPrizeForRageOfYears(String yearStart, String yearEnd, Field field) {
        return service.fetchNobelPrizeForRangeOfYears(yearStart, yearEnd, field).map(response -> {
            if (response == null || response.getNobelPrizes() == null || response.getNobelPrizes().isEmpty()) {
                throw new  IllegalArgumentException("Arguments CAN'T be equal to null; response = " + response);
            }

            return response.getNobelPrizes().stream().map(NobelPrize::new).collect(Collectors.toList());
        });
    }
}
