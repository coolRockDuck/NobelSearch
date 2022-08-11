package com.example.nobelprizesearch.ui.uiState;

import java.util.function.Consumer;

public class FailuireState<T> extends UiState<T> {
    private final Throwable error;
    public FailuireState(Throwable error) {
        super(UiStateType.FAILURE);
        this.error = error;
    }

    public void consumeFailure(Consumer<Throwable> failureConsumer) {
        failureConsumer.accept(error);
    }
}
