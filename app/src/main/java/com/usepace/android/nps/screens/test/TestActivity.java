package com.usepace.android.nps.screens.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.usepace.android.nps.R;
import com.usepace.android.nps.screens.rating.RatingActivity;

public class TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        startActivity(new Intent(this, RatingActivity.class));
    }
}
