package com.example.nobelprizesearch.ui.uiState;

public class InProgressState<T> extends UiState<T> {
    public void react(Reaction reaction) {
        reaction.react();
    }
}
