package com.usepace.android.nps.clients;

import android.content.Context;
import com.usepace.android.nps.interfaces.SurveyCallbacks;

class NpsClient implements NpsInterface {

    private Context context;
    private String user_token;

    @Override
    public void init(Context context, String user_token) {
        this.context = context;
        this.user_token = user_token;
    }

    @Override
    public void survey(SurveyCallbacks surveyCallbacks) {

    }

}
