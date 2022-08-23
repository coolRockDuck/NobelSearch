package com.example.nobelprizesearch.ui.uiState;

import java.util.function.Consumer;

/**
 * Class which holds state of an UI action.
 * It's workaround of lack of sealed classes in this version of Java.
 * State can be one of {@link SuccessState}, {@link InProgressState} or {@link FailureState}.
 */
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

    /**
     * Calls a specific lambda, selected base on the state.
     * Allows use similar to RxJava {@link io.reactivex.Observable#subscribe()}.
     */
    public void resolve(Consumer<T> successConsumer, Reaction inProgressReaction, Consumer<Throwable> failureConsumer) {
        if (isSuccessful()) {
            ((SuccessState<T>) this).consumeSuccess(successConsumer);
        } else if (isInProgress()) {
            ((InProgressState<T>) this).react(inProgressReaction);
        } else if (isFailure()) {
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

