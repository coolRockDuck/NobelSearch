package com.example.nobelprizesearch.di;

import android.app.Application;

public class MainApplication extends Application {
    public ApplicationComponent applicationComponent = DaggerApplicationComponent.create();
}
