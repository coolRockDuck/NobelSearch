package com.example.nobelprizesearch;

import android.app.Application;

import com.example.nobelprizesearch.data.GetNobelPrizesUseCase;
import com.example.nobelprizesearch.data.NobelPrizeClient;
import com.example.nobelprizesearch.data.NobelPrizeService;
import com.example.nobelprizesearch.data.repo.NobelPrizesRepository;
import com.example.nobelprizesearch.di.Provider;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainApplication extends Application implements Provider {

    private GetNobelPrizesUseCase useCase;

    /**
     * Temporary manual dependency injection
     */
    @Override
    public GetNobelPrizesUseCase provideGetNobelUseCase() {
        if (useCase == null) {
            Interceptor i = chain -> {
                System.out.println("Request = " + chain.request());
                return chain.proceed(chain.request());
            };

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(i)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.nobelprize.org/2.1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();

            NobelPrizeService service = retrofit.create(NobelPrizeService.class);
            NobelPrizesRepository repository = new NobelPrizesRepository(new NobelPrizeClient(service));
            useCase = new GetNobelPrizesUseCase(repository);
        }
        return useCase;
    }
}
