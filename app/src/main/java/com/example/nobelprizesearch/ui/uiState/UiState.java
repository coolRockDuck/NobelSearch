package com.example.nobelprizesearch.ui.uiState;

import java.util.function.Consumer;

abstract public class UiState<T> {
    private final UiStateType type;

    public UiState(UiStateType type) {
        this.type = type;
    }

    public boolean isSuccessful() {
        return type == UiStateType.SUCCESS;
    }

    public boolean isInProgress() {
        return type == UiStateType.IN_PROGRESS;
    }

    public boolean isFailure() {
        return type == UiStateType.FAILURE;
    }

    public UiStateType getType() {
        return type;
    }

    public void resolve(Consumer<T> successConsumer, React inProgressReaction, Consumer<Throwable> failureConsumer) {
        if (isSuccessful()) {
            ((SuccessState<T>) this).consumeSuccess(successConsumer);
        }
        else if (isInProgress()) {
            ((InProgressState<T>) this).consumeReaction(inProgressReaction);
        }
        else if (isFailure()) {
            ((FailuireState<T>) this).consumeFailure(failureConsumer);
        }
    }

    public void ifSuccessThen(Consumer<T> consumer) {
        if (isSuccessful()) {
            ((SuccessState<T>) this).consumeSuccess(consumer);
        }
    }

    public void ifInProgressThen(React react) {
        if (isInProgress()) {
            ((InProgressState<T>) this).consumeReaction(react);
        }
    }

    public void ifFailureThen(Consumer<Throwable> consumer) {
        if (isFailure()) {
            ((FailuireState<T>) this).consumeFailure(consumer);
        }
    }

    public enum UiStateType {
        SUCCESS,
        IN_PROGRESS,
        FAILURE;
    }
}

