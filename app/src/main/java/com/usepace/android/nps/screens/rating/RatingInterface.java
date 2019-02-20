package com.usepace.android.nps.screens.rating;


public interface RatingInterface {

    /** Represents the View in MVP. */
    interface View {
        void startRatingAnimationFromBottom();
        void closeRatingView();
        void showDismissButton();
    }

    /** Represents the Presenter in MVP. */
    interface Presenter {
        void init();
        void onBackPressed();
    }


}
