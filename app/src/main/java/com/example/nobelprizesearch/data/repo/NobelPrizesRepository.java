package com.example.nobelprizesearch.data.repo;

import com.example.nobelprizesearch.data.NobelPrizeClient;
import com.example.nobelprizesearch.model.domain.Field;
import com.example.nobelprizesearch.model.domain.NobelPrize;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class NobelPrizesRepository {
    private final NobelPrizeClient client;

    @Inject
    public NobelPrizesRepository(NobelPrizeClient client) {
        this.client = client;
    }

    public Single<List<NobelPrize>> getNobelPrizeForYear(String year, Field field) {
        return client.fetchNobelPrizeForYear(year, field);
    }

    public Single<List<NobelPrize>> getNobelPrizeForRageOfYears(String year, String yearEnd) {
        return client.fetchNobelPrizeForRageOfYears(year, yearEnd);
    }

    public Single<List<NobelPrize>> getNobelPrizeForRageOfYears(String yearStart, String yearEnd, Field field) {
        return client.fetchNobelPrizeForRageOfYears(yearStart, yearEnd, field);
    }
}
