package com.example.nobelprizesearch.ui.viewModels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.nobelprizesearch.data.NobelPrizeClient;

public class NobelPrizesViewModelFactory implements ViewModelProvider.Factory {
    private final NobelPrizeClient client;

    public NobelPrizesViewModelFactory(NobelPrizeClient client) {
        this.client = client;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new NobelPrizesViewModel(client);
    }
}
