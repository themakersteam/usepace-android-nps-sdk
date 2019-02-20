package com.usepace.android.nps.clients;

import android.content.Context;
import com.usepace.android.nps.interfaces.SurveyCallbacks;

interface NpsInterface {

    void init(Context context, String user_token);
    void survey(String language, SurveyCallbacks surveyCallbacks);

}
