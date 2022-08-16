package com.example.nobelprizesearch.ui.viewModels.factories;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.nobelprizesearch.data.GetNobelPrizesUseCase;
import com.example.nobelprizesearch.ui.viewModels.ListNobelPrizesViewModel;

public class ListNobelPrizesFactory implements ViewModelProvider.Factory {

    private final GetNobelPrizesUseCase useCase;

    public ListNobelPrizesFactory(GetNobelPrizesUseCase useCase) {
        this.useCase = useCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ListNobelPrizesViewModel(useCase);
    }
}
