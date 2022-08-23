package com.example.nobelprizesearch.ui.viewModels;

import android.icu.util.Calendar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.nobelprizesearch.data.GetNobelPrizesUseCase;
import com.example.nobelprizesearch.model.domain.NobelPrize;
import com.example.nobelprizesearch.ui.uiState.FailureState;
import com.example.nobelprizesearch.ui.uiState.InProgressState;
import com.example.nobelprizesearch.ui.uiState.SuccessState;
import com.example.nobelprizesearch.ui.uiState.UiState;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class ListNobelPrizesViewModel extends ViewModel {

    private final static int NEXT_YEAR_END_JUMP = 5;
    private final GetNobelPrizesUseCase getNobelPrizesUseCase;
    private final MutableLiveData<UiState<List<NobelPrize>>> listOfPrizes = new MutableLiveData<>();

    private int endYear = Calendar.getInstance().get(Calendar.YEAR);
    private final CompositeDisposable disposable = new CompositeDisposable();

    @Inject
    public ListNobelPrizesViewModel(GetNobelPrizesUseCase getNobelPrizesUseCase) {
        this.getNobelPrizesUseCase = getNobelPrizesUseCase;
    }

    public LiveData<UiState<List<NobelPrize>>> getListOfPrizes() {
        return listOfPrizes;
    }

    public void fetchNextNobelPrizes() {
        int nextEndYear = generateNextEndYear();

        Disposable call = getNobelPrizesUseCase.getNobelPrizesForRangeOfYears(String.valueOf(endYear), String.valueOf(nextEndYear - 1))
                .subscribeOn(Schedulers.io()) // check post value on NOT main thread
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((ignored) -> listOfPrizes.setValue(new InProgressState<>()))
                .subscribe(value -> {
                            listOfPrizes.setValue(new SuccessState<>(value));
                        },
                        (error) -> {
                            listOfPrizes.setValue(new FailureState<>(error));
                        }
                );

        endYear = nextEndYear;
        disposable.add(call);
    }

    private int generateNextEndYear() {
        return endYear - NEXT_YEAR_END_JUMP;
    }

    @Override
    protected void onCleared() {
        disposable.clear();
        super.onCleared();
    }
}
