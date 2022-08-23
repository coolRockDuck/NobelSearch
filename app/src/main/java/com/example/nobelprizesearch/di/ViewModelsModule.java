package com.example.nobelprizesearch.di;

import androidx.lifecycle.ViewModel;

import com.example.nobelprizesearch.data.GetNobelPrizesUseCase;
import com.example.nobelprizesearch.ui.viewModels.ListNobelPrizesViewModel;
import com.example.nobelprizesearch.ui.viewModels.NobelPrizesViewModel;
import com.example.nobelprizesearch.ui.viewModels.factories.ViewModelFactory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

import javax.inject.Provider;
import javax.inject.Singleton;

import dagger.MapKey;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

@Module
public class ViewModelsModule {

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @MapKey
    @interface ViewModelKey {
        Class<? extends ViewModel> value();
    }

    @Provides
    ViewModelFactory viewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> providerMap) {
        return new ViewModelFactory(providerMap);
    }

    @Provides
    @IntoMap
    @Singleton
    @ViewModelKey(ListNobelPrizesViewModel.class)
    ViewModel provideListNobelPrizeViewModel(GetNobelPrizesUseCase useCase) {
        return new ListNobelPrizesViewModel(useCase);
    }

    @Provides
    @IntoMap
    @Singleton
    @ViewModelKey(NobelPrizesViewModel.class)
    ViewModel provideNobelPrizeViewModel(GetNobelPrizesUseCase useCase) {
        return new NobelPrizesViewModel(useCase);
    }
}