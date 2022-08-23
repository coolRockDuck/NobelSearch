package com.example.nobelprizesearch.ui.uiState;

import java.util.function.Consumer;

public final class SuccessState<T> extends UiState<T> {
    private final T state;

    public SuccessState(T state) {
        this.state = state;
    }

    void consumeSuccess(Consumer<T> consumer) {
        consumer.accept(state);
    }
}
