package com.example.nobelprizesearch.ui.uiState;

import android.util.Log;

import java.util.function.Consumer;

public final class SuccessState<T> extends UiState<T> {
    private final T state;

    public SuccessState(T state) {
        this.state = state;
    }

    void consumeSuccess(Consumer<T> consumer) {
        if (state != null) {
            consumer.accept(state);
        } else {
            Log.w("SuccessState", "consumeSuccess: state is equal to null");
        }
    }
}
