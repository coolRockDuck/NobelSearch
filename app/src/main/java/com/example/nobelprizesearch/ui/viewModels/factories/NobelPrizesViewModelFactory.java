package com.example.nobelprizesearch.ui.viewModels.factories;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.nobelprizesearch.data.GetNobelPrizesUseCase;
import com.example.nobelprizesearch.data.NobelPrizeClient;
import com.example.nobelprizesearch.data.repo.NobelPrizesRepository;
import com.example.nobelprizesearch.ui.viewModels.NobelPrizesViewModel;

public class NobelPrizesViewModelFactory implements ViewModelProvider.Factory {
    private final GetNobelPrizesUseCase useCase;

    public NobelPrizesViewModelFactory(GetNobelPrizesUseCase useCase) {
        this.useCase = useCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new NobelPrizesViewModel(useCase);
    }
}
