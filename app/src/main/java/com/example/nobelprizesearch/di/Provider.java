package com.example.nobelprizesearch.di;

import com.example.nobelprizesearch.data.GetNobelPrizesUseCase;
import com.example.nobelprizesearch.data.NobelPrizeClient;
import com.example.nobelprizesearch.data.repo.NobelPrizesRepository;

public interface Provider {
    GetNobelPrizesUseCase provideGetNobelUseCase();
}
