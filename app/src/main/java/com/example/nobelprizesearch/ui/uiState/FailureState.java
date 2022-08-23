package com.example.nobelprizesearch.ui.uiState;

import java.util.function.Consumer;

public final class FailureState<T> extends UiState<T> {
    private final Throwable error;
    public FailureState(Throwable error) {
        this.error = error;
    }

    public void consumeFailure(Consumer<Throwable> failureConsumer) {
        failureConsumer.accept(error);
    }
}
