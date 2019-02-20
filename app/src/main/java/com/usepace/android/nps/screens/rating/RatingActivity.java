package com.usepace.android.nps.screens.rating;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.usepace.android.nps.io.model.RatingModel;

public class RatingActivity extends AppCompatActivity {

    RatingModel ratingModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent() != null && getIntent().getExtras() != null) {
            ratingModel = (RatingModel) getIntent().getExtras().getSerializable("RATING");
        }
        if (ratingModel == null) {
            finish();
        }
        showDialog();
    }

    private void showDialog() {
        RatingOptionsBottomSheetAdapter ratingOptionsBottomSheetAdapter = RatingOptionsBottomSheetAdapter.newInstance(ratingModel);
        ratingOptionsBottomSheetAdapter.show(getSupportFragmentManager(), "BOTTOM_RATING");
    }
}
