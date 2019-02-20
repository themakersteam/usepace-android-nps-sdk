package com.usepace.android.nps.screens.test;

import android.app.Activity;
import android.os.Bundle;
import com.usepace.android.nps.R;
import com.usepace.android.nps.clients.Nps;
import com.usepace.android.nps.exeptions.NpsException;
import com.usepace.android.nps.interfaces.SurveyCallbacks;

public class TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Nps.init(this, "");
        Nps.survey(new SurveyCallbacks() {
            @Override
            public void onFailedToLoadSurvey(NpsException npsException) {

            }
        });
    }
}
