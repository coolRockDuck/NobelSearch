package com.example.nobelprizesearch.data;

import android.icu.util.Calendar;

import com.example.nobelprizesearch.data.repo.NobelPrizesRepository;
import com.example.nobelprizesearch.model.domain.Field;
import com.example.nobelprizesearch.model.domain.NobelPrize;

import java.time.Year;
import java.util.List;

import io.reactivex.Single;

public class GetNobelPrizesUseCase {
    private final NobelPrizesRepository repository;
    static final Integer MIN_YEAR = 1901;
    static final Integer MAX_YEAR = Calendar.getInstance().get(Calendar.YEAR);


    public GetNobelPrizesUseCase(NobelPrizesRepository repository) {
        this.repository = repository;
    }

    public Single<List<NobelPrize>> getNobelPrizesForRangeOfYears(String yearStart, String yearEnd, Field field) {
        if (!isInputValid(yearStart, yearEnd)) {
            return createSingleError("Starting year too small: " + yearStart + ",or ending year too large: " + yearEnd);
        }

        return repository.getNobelPrizeForRageOfYears(yearStart, yearEnd, field);
    }
    public Single<List<NobelPrize>> getNobelPrizesForRangeOfYears(String yearStart, String yearEnd) {
        if (!isInputValid(yearStart, yearEnd)) {
            return createSingleError("Starting year too small: " + yearStart + ",or ending year too large: " + yearEnd);
        }

        return repository.getNobelPrizeForRageOfYears(yearStart, yearEnd);
    }

    public Single<List<NobelPrize>> getNobelPrizesForOfYear(String year, Field field) {
        if (!isSingleYearValid(year)) {
            return createSingleError("Year too small or too large: " + year);
        }

        return repository.getNobelPrizeForYear(year, field);
    }


    private Single<List<NobelPrize>> createSingleError(String errorMsg) {
        Exception error = new IllegalArgumentException(errorMsg);
        return Single.error(error);
    }

    private boolean isSingleYearValid(String year) {
        if (year == null || year.isEmpty()) {
            return false;
        }

        int yearAsNumber = Integer.parseInt(year);

        return yearAsNumber <= MAX_YEAR && yearAsNumber >= MIN_YEAR;
    }

    private boolean isInputValid(String yearStart, String yearEnd) {
        return isSingleYearValid(yearStart) && isSingleYearValid(yearEnd);
    }
}
