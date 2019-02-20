package com.usepace.android.nps.clients;

import android.content.Context;
import com.usepace.android.nps.interfaces.SurveyCallbacks;
import java.util.HashMap;

interface NpsInterface {

    void init(Context context, String client_id);
    void setUser(String token, HashMap<String, Object> user_data);
    void survey(String language, SurveyCallbacks surveyCallbacks);

}
