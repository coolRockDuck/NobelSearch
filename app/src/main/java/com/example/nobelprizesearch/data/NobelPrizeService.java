package com.example.nobelprizesearch.data;

import com.example.nobelprizesearch.model.api_response.RangeOfNobelPrizeApiResponse;
import com.example.nobelprizesearch.model.domain.Field;
import com.example.nobelprizesearch.model.api_response.NobelPrizeApiResponse;

import java.util.List;

import javax.inject.Singleton;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

@Singleton
public interface NobelPrizeService {
    @GET("nobelPrize/{field}/{year}")
    Single<List<NobelPrizeApiResponse>> fetchNobelPrizeForYear(@Path("year") String year, @Path("field") Field field);

    @GET("nobelPrizes?sort=desc")
    Single<RangeOfNobelPrizeApiResponse> fetchNobelPrizeForRangeOfYears(@Query("nobelPrizeYear") String yearStart, @Query("yearTo") String yearEnd, @Query("nobelPrizeCategory") Field field);

    @GET("nobelPrizes?sort=desc")
    Single<RangeOfNobelPrizeApiResponse> fetchNobelPrizeForRangeOfYears(@Query("nobelPrizeYear") String yearStart, @Query("yearTo") String yearEnd);
}
