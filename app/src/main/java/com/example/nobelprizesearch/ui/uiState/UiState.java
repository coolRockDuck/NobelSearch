package com.example.nobelprizesearch.ui.uiState;

import java.util.function.Consumer;

abstract public class UiState<T> {

    public boolean isSuccessful() {
        return this instanceof SuccessState;
    }

    public boolean isInProgress() {
        return this instanceof InProgressState;
    }

    public boolean isFailure() {
        return this instanceof FailureState;
    }


    public void resolve(Consumer<T> successConsumer, Reaction inProgressReaction, Consumer<Throwable> failureConsumer) {
        if (isSuccessful()) {
            ((SuccessState<T>) this).consumeSuccess(successConsumer);
        }
        else if (isInProgress()) {
            ((InProgressState<T>) this).react(inProgressReaction);
        }
        else if (isFailure()) {
            ((FailureState<T>) this).consumeFailure(failureConsumer);
        }
    }

    public void ifSuccessThen(Consumer<T> consumer) {
        if (isSuccessful()) {
            ((SuccessState<T>) this).consumeSuccess(consumer);
        }
    }

    public void ifInProgressThen(Reaction react) {
        if (isInProgress()) {
            ((InProgressState<T>) this).react(react);
        }
    }

    public void ifFailureThen(Consumer<Throwable> consumer) {
        if (isFailure()) {
            ((FailureState<T>) this).consumeFailure(consumer);
        }
    }
}

