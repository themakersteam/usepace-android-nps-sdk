package com.usepace.android.nps.screens.rating;


public interface RatingInterface {

    /** Represents the View in MVP. */
    interface View {
        void startRatingAnimationFromBottom();
        void onQuestionReady(String question, String high_rate, String low_rate);
        void closeRatingView();
        void showDismissButton();
        void showSubmit(boolean show);
        void showLoading(boolean show);
        void closeWithMessage(String message);
    }

    /** Represents the Presenter in MVP. */
    interface Presenter {
        void init();
        void onBackPressed();
        void submitButtonClicked(Integer value);
    }


}
