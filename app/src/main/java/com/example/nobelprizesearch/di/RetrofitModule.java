package com.example.nobelprizesearch.di;

import android.util.Log;

import com.example.nobelprizesearch.data.NobelPrizeService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {
    @Singleton
    @Provides
    Interceptor provideInterceptor() {
        return chain -> {
            Log.i("OkHttp Interceptor", "Request = " + chain.request());
            return chain.proceed(chain.request());
        };
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Interceptor i) {
        return new OkHttpClient.Builder()
                .addInterceptor(i)
                .build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.nobelprize.org/2.1/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    @Provides
    @Singleton
    NobelPrizeService provideService(Retrofit retrofit) {
        return retrofit.create(NobelPrizeService.class);
    }
}
