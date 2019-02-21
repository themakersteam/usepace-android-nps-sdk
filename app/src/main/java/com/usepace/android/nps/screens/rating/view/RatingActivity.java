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

public class RatingActivity extends AppCompatActivity implements RatingInterface.View, RadioGroup.OnCheckedChangeListener {

    private LinearLayout ratingLayout;
    private ImageView ivDismiss;
    private TextView tvQuestion;
    private TextView tvHighHint;
    private TextView tvLowHint;
    private RadioGroup rg1;
    private RadioGroup rg2;
    private Button btnSubmit;
    private ProgressBar progressBar;

    private RatingPresenter ratingPresenter;
    private int last_checked_id = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        fadeIn();
        init();
        ratingPresenter = new RatingPresenter(this, this);
        ratingPresenter.init();
        rg1.setOnCheckedChangeListener(this);
        rg2.setOnCheckedChangeListener(this);
    }

    private void fadeIn() {
        View view = findViewById(android.R.id.content);
        Animation mLoadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
        mLoadAnimation.setDuration(500);
        view.startAnimation(mLoadAnimation);
    }

    private void init() {
        ratingLayout = findViewById(R.id.layout_rating);
        ivDismiss = findViewById(R.id.iv_dismiss);
        tvQuestion = findViewById(R.id.tv_question);
        tvHighHint = findViewById(R.id.tv_high_hint);
        tvLowHint = findViewById(R.id.tv_low_hint);
        rg1 = findViewById(R.id.rg1);
        rg2 = findViewById(R.id.rg2);
        btnSubmit = findViewById(R.id.btn_submit);
        progressBar = findViewById(R.id.progress);
        ivDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratingPresenter.onBackPressed();
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rg1.findViewById(last_checked_id) != null) {
                    Integer selected = Integer.parseInt(((RadioButton)rg1.findViewById(last_checked_id)).getText().toString());
                    ratingPresenter.submitButtonClicked(selected);
                }
                else if (rg2.findViewById(last_checked_id) != null) {
                    Integer selected = Integer.parseInt(((RadioButton)rg2.findViewById(last_checked_id)).getText().toString());
                    ratingPresenter.submitButtonClicked(selected);
                }
            }
        });
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
        if (show && btnSubmit.getVisibility() != View.VISIBLE) {
            Animation bottomUp = AnimationUtils.loadAnimation(this, R.anim.slide_from_bottom);
            btnSubmit.startAnimation(bottomUp);
            btnSubmit.setVisibility(show ? View.VISIBLE : View.GONE);
        }
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
        ivDismiss.setVisibility(show ? View.GONE : ivDismiss.getVisibility());
    }

    @Override
    public void showDismissButton() {
        ivDismiss.setVisibility(View.VISIBLE);
    }

    @Override
    public void closeRatingView() {
        finish();
        overridePendingTransition(0, android.R.anim.fade_out);
    }

    @Override
    public void closeWithMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        closeRatingView();
    }

    @Override
    public void onBackPressed() {
        ratingPresenter.onBackPressed();
    }
}
