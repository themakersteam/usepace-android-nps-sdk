package com.usepace.android.nps.screens.rating.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.usepace.android.nps.R;
import com.usepace.android.nps.screens.rating.RatingInterface;
import com.usepace.android.nps.screens.rating.presenter.RatingPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RatingActivity extends AppCompatActivity implements RatingInterface.View {


    @BindView(R.id.layout_rating) LinearLayout ratingLayout;
    @BindView(R.id.iv_dismiss) ImageView ivDismiss;

    private RatingPresenter ratingPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        ButterKnife.bind(this);
        ratingPresenter = new RatingPresenter(this, this);
        ratingPresenter.init();
    }

    @Override
    public void startRatingAnimationFromBottom() {
        Animation bottomUp = AnimationUtils.loadAnimation(this, R.anim.slide_from_bottom);
        ratingLayout.startAnimation(bottomUp);
        ratingLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showDismissButton() {
        ivDismiss.setVisibility(View.VISIBLE);
    }

    @Override
    public void closeRatingView() {
        finish();
    }


    @OnClick(R.id.iv_dismiss)
    protected void dismissClicked() {
        ratingPresenter.onBackPressed();
    }

    @Override
    public void onBackPressed() {
        ratingPresenter.onBackPressed();
    }
}
