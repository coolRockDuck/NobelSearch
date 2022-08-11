package com.example.nobelprizesearch.ui.uiState;

import java.util.function.Consumer;

public class SuccessState<T> extends UiState<T> {
    protected T state;

    public SuccessState(T state) {
        super(UiStateType.SUCCESS);
        this.state = state;
    }

    void consumeSuccess(Consumer<T> consumer) {
        consumer.accept(state);
    }
}
