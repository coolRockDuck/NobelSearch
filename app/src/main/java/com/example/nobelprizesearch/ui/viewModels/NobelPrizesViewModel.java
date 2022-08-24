package com.example.nobelprizesearch.ui.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.nobelprizesearch.data.GetNobelPrizesUseCase;
import com.example.nobelprizesearch.model.domain.Field;
import com.example.nobelprizesearch.model.domain.NobelPrize;
import com.example.nobelprizesearch.ui.uiState.FailureState;
import com.example.nobelprizesearch.ui.uiState.InProgressState;
import com.example.nobelprizesearch.ui.uiState.SuccessState;
import com.example.nobelprizesearch.ui.uiState.UiState;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class NobelPrizesViewModel extends ViewModel {
    private static final String TAG = "NobelPrizesViewModel";

    private final GetNobelPrizesUseCase getNobelPrizesUseCase;

    private final MutableLiveData<UiState<List<NobelPrize>>> listOfPrizes = new MutableLiveData<>();

    private final CompositeDisposable disposable = new CompositeDisposable();

    @Inject
    public NobelPrizesViewModel(GetNobelPrizesUseCase getNobelPrizesUseCase) {
        this.getNobelPrizesUseCase = getNobelPrizesUseCase;
    }

    public MutableLiveData<UiState<List<NobelPrize>>> getListOfPrizes() {
        return listOfPrizes;
    }

    /**
     * Methods which prevents from displaying the same prizes multiple times.
     */
    public void nobelPrizesHasBeenConsumed() {
        listOfPrizes.setValue(null);
    }

    public void fetchNobelPrizesForYear(String year, Field field) {
        Disposable call = getNobelPrizesUseCase.getNobelPrizesForOfYear(year, field)
                .subscribeOn(Schedulers.io()) // check post value on NOT main thread
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(ignored -> listOfPrizes.setValue(new InProgressState<>()))
                .subscribe(value -> {
                            listOfPrizes.setValue(new SuccessState<>(value));
                        },
                        (error) -> {
                            listOfPrizes.setValue(new FailureState<>(error));
                        }
                );

        disposable.add(call);
    }


    public void fetchNobelPrizesForRangeOfYears(String yearStart, String yearEnd) {
        Disposable call = getNobelPrizesUseCase.getNobelPrizesForRangeOfYears(yearStart, yearEnd)
                .subscribeOn(Schedulers.io()) // check post value on NOT main thread
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(ignored -> listOfPrizes.setValue(new InProgressState<>()))
                .subscribe(value -> {
                            listOfPrizes.setValue(new SuccessState<>(value));
                        },
                        (error) -> {
                            listOfPrizes.setValue(new FailureState<>(error));
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
