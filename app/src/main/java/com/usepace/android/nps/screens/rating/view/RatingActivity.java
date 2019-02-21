package com.usepace.android.nps.screens.rating.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.usepace.android.nps.R;
import com.usepace.android.nps.screens.rating.RatingInterface;
import com.usepace.android.nps.screens.rating.presenter.RatingPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RatingActivity extends AppCompatActivity implements RatingInterface.View, RadioGroup.OnCheckedChangeListener {


    @BindView(R.id.layout_rating) LinearLayout ratingLayout;
    @BindView(R.id.iv_dismiss) ImageView ivDismiss;
    @BindView(R.id.tv_question) TextView tvQuestion;
    @BindView(R.id.tv_high_hint) TextView tvHighHint;
    @BindView(R.id.tv_low_hint) TextView tvLowHint;
    @BindView(R.id.rg1) RadioGroup rg1;
    @BindView(R.id.rg2) RadioGroup rg2;
    @BindView(R.id.btn_submit) Button btnSubmit;
    @BindView(R.id.progress) ProgressBar progressBar;

    private RatingPresenter ratingPresenter;
    private int last_checked_id = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        ButterKnife.bind(this);
        ratingPresenter = new RatingPresenter(this, this);
        ratingPresenter.init();
        rg1.setOnCheckedChangeListener(this);
        rg2.setOnCheckedChangeListener(this);
    }

    @Override
    public void startRatingAnimationFromBottom() {
        Animation bottomUp = AnimationUtils.loadAnimation(this, R.anim.slide_from_bottom);
        ratingLayout.startAnimation(bottomUp);
        ratingLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onQuestionReady(String question, String high_rate, String low_rate) {
        tvQuestion.setText(question);
        tvHighHint.setText(high_rate);
        tvLowHint.setText(low_rate);
    }

    @Override
    public void showSubmit(boolean show) {
        btnSubmit.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        showSubmit(true);
        if (rg1.findViewById(last_checked_id) != null) {
            ((RadioButton)rg1.findViewById(last_checked_id)).setChecked(false);
        }
        else if (rg2.findViewById(last_checked_id) != null) {
            ((RadioButton)rg2.findViewById(last_checked_id)).setChecked(false);
        }
        last_checked_id = checkedId;
    }

    @Override
    public void showLoading(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        ratingLayout.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    @Override
    public void showDismissButton() {
        ivDismiss.setVisibility(View.VISIBLE);
    }

    @Override
    public void closeRatingView() {
        finish();
    }

    @Override
    public void closeWithMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        finish();
    }

    @OnClick(R.id.iv_dismiss)
    protected void dismissClicked() {
        ratingPresenter.onBackPressed();
    }

    @OnClick(R.id.btn_submit)
    protected void buttonSubmit() {
        if (rg1.findViewById(last_checked_id) != null) {
            Integer selected = Integer.parseInt(((RadioButton)rg1.findViewById(last_checked_id)).getText().toString());
            ratingPresenter.submitButtonClicked(selected);
        }
        else if (rg2.findViewById(last_checked_id) != null) {
            Integer selected = Integer.parseInt(((RadioButton)rg2.findViewById(last_checked_id)).getText().toString());
            ratingPresenter.submitButtonClicked(selected);
        }
    }

    @Override
    public void onBackPressed() {
        ratingPresenter.onBackPressed();
    }
}
