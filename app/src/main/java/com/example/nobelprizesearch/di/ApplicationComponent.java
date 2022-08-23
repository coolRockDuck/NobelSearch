package com.example.nobelprizesearch.di;

import com.example.nobelprizesearch.ui.fragments.NobelListFragment;
import com.example.nobelprizesearch.ui.fragments.SearchForNobelFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RetrofitModule.class, ViewModelsModule.class})
public interface ApplicationComponent {
    void inject(NobelListFragment fragment);
    void inject(SearchForNobelFragment fragment);
}
