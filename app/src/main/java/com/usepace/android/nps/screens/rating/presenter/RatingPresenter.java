package com.usepace.android.nps.screens.rating.presenter;

import android.app.Activity;
import com.usepace.android.nps.io.model.RatingModel;
import com.usepace.android.nps.screens.rating.RatingInterface;

public class RatingPresenter implements RatingInterface.Presenter {

    private RatingInterface.View view;
    private Activity activity;

    private RatingModel ratingModel;

    public RatingPresenter(Activity activity, RatingInterface.View view) {
        this.view = view;
        this.activity = activity;
    }

    @Override
    public void init() {
        if (activity.getIntent() != null && activity.getIntent().getExtras() != null && activity.getIntent().getExtras().containsKey("RATING")) {
            ratingModel = (RatingModel) activity.getIntent().getExtras().getSerializable("RATING");
            view.startRatingAnimationFromBottom();
            if (ratingModel != null && ratingModel.isDismiss()) {
                view.showDismissButton();
            }
        }
        if (ratingModel == null) {
            view.closeRatingView();
        }
    }

    @Override
    public void onBackPressed() {
        if (ratingModel != null && ratingModel.isDismiss()) {
            view.closeRatingView();
        }
    }
}
