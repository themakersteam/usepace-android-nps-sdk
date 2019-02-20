package com.usepace.android.nps.clients;

import android.content.Context;
import com.usepace.android.nps.interfaces.SurveyCallbacks;

public class Nps {

    private static NpsClient npsClient;

    /**
     *
     * @param context
     * @param user_token
     */
    public static void init(Context context, String user_token) {
        if (npsClient == null)
            npsClient = new NpsClient();
        npsClient.init(context, user_token);
    }

    /**
     *
     * @param surveyCallbacks
     */
    public static void survey(SurveyCallbacks surveyCallbacks) {
        if (npsClient == null)
            npsClient = new NpsClient();
        npsClient.survey(surveyCallbacks);
    }
}
