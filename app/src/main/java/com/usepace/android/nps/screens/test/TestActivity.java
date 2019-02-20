package com.usepace.android.nps.screens.test;

import android.app.Activity;
import android.os.Bundle;
import com.usepace.android.nps.R;
import com.usepace.android.nps.clients.Nps;
import com.usepace.android.nps.exeptions.NpsException;
import com.usepace.android.nps.interfaces.SurveyCallbacks;

import java.util.HashMap;

public class TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Nps.init(this, "");
        Nps.setUser("", new HashMap<String, Object>());
        Nps.survey("en", new SurveyCallbacks() {
            @Override
            public void onFailedToLoadSurvey(NpsException npsException) {

            }
        });
    }
}
