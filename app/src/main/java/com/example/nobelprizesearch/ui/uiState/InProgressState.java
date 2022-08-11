package com.example.nobelprizesearch.ui.uiState;

public class InProgressState<T> extends UiState<T> {
    public InProgressState() {
        super(UiStateType.IN_PROGRESS);
    }

    public void consumeReaction(React react) {
        react.react();
    }
}
