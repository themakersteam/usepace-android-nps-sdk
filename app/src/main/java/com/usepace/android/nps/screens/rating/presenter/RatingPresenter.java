package com.usepace.android.nps.screens.rating.presenter;

import android.app.Activity;
import com.usepace.android.nps.R;
import com.usepace.android.nps.io.NpsPlatformApi;
import com.usepace.android.nps.io.NpsPlatformApiCallbackInterface;
import com.usepace.android.nps.io.model.RatingModel;
import com.usepace.android.nps.screens.rating.RatingInterface;
import okhttp3.ResponseBody;

public class RatingPresenter implements RatingInterface.Presenter {

    private RatingInterface.View view;
    private Activity activity;

    private RatingModel ratingModel;
    private String client_id, user_token;

    public RatingPresenter(Activity activity, RatingInterface.View view) {
        this.view = view;
        this.activity = activity;
    }

    @Override
    public void init() {
        if (activity.getIntent() != null && activity.getIntent().getExtras() != null && activity.getIntent().getExtras().containsKey("RATING")) {
            ratingModel = (RatingModel) activity.getIntent().getExtras().getSerializable("RATING");
            client_id = activity.getIntent().getStringExtra("CLIENT_ID");
            user_token = activity.getIntent().getStringExtra("USER_TOKEN");
            view.startRatingAnimationFromBottom();
            if (ratingModel != null && ratingModel.isDismiss()) {
                view.showDismissButton();
            }
            if (ratingModel != null) {
                String question = ratingModel.getQuestion() != null ? ratingModel.getQuestion() : "";
                String high = ratingModel.getHighestHint() != null ? ratingModel.getHighestHint() : "";
                String low = ratingModel.getLowestHint() != null ? ratingModel.getLowestHint() : "";
                view.onQuestionReady(question, high, low);
            }
        }
        if (ratingModel == null) {
            view.closeRatingView();
        }
    }

    @Override
    public void submitButtonClicked(Integer value) {
        view.showLoading(true);
        NpsPlatformApi.Instance().postNpsSurveys(user_token, client_id, value, false, new NpsPlatformApiCallbackInterface<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody result) {
                if (activity != null && !activity.isFinishing()) {
                    view.closeWithMessage(activity.getString(R.string.nps_thank_you));
                }
            }

            @Override
            public void onError(String error) {
                if (activity != null && !activity.isFinishing()) {
                    view.closeWithMessage(activity.getString(R.string.nps_failed));
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (ratingModel != null && ratingModel.isDismiss()) {
            view.showLoading(true);
            NpsPlatformApi.Instance().postNpsSurveys(user_token, client_id, null, true, new NpsPlatformApiCallbackInterface<ResponseBody>() {
                @Override
                public void onSuccess(ResponseBody result) {
                    view.closeRatingView();
                }
                @Override
                public void onError(String error) {
                    view.closeRatingView();
                }
            });
        }
    }
}
