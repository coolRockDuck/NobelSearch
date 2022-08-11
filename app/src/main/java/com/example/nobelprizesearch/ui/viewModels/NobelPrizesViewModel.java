package com.example.nobelprizesearch.ui.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.nobelprizesearch.data.NobelPrizeClient;
import com.example.nobelprizesearch.ui.uiState.FailuireState;
import com.example.nobelprizesearch.ui.uiState.InProgressState;
import com.example.nobelprizesearch.ui.uiState.SuccessState;
import com.example.nobelprizesearch.ui.uiState.UiState;
import com.example.nobelprizesearch.model.domain.Field;
import com.example.nobelprizesearch.model.domain.NobelPrize;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NobelPrizesViewModel extends ViewModel {
    private static final String TAG = "NobelPrizesViewModel";

    private final NobelPrizeClient networkClient;

    private final MutableLiveData<UiState<List<NobelPrize>>> listOfPrizes = new MutableLiveData<>();
    private final MutableLiveData<UiState<NobelPrize>> specificNobelPrize = new MutableLiveData<>();

    private final CompositeDisposable disposable = new CompositeDisposable();

    public NobelPrizesViewModel(NobelPrizeClient networkClient) {
        this.networkClient = networkClient;
    }

    public MutableLiveData<UiState<List<NobelPrize>>> getListOfPrizes() {
        return listOfPrizes;
    }

    public MutableLiveData<UiState<NobelPrize>> getSpecificNobelPrize() {
        return specificNobelPrize;
    }

    public void fetchNobelPrizesForYear(String year, Field field) {
        Disposable call = networkClient.fetchNobelPrizeForYear(year, field)
                .subscribeOn(Schedulers.io()) // check post value on NOT main thread
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(ignored -> listOfPrizes.setValue(new InProgressState<>()))
                .subscribe(value -> {
                            listOfPrizes.setValue(new SuccessState<>(value));
                        },
                        (error) -> {
                            System.out.println(Thread.currentThread());
                            listOfPrizes.setValue(new FailuireState<>(error));
                        }
                );

        disposable.add(call);
    }

    @Override
    protected void onCleared() {
        disposable.clear();
        super.onCleared();
    }
}
