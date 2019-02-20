package com.usepace.android.nps.clients;

import android.content.Context;
import com.usepace.android.nps.interfaces.SurveyCallbacks;
import java.util.HashMap;

public class Nps {

    private static NpsClient npsClient;

    /**
     *
     * @param context
     * @param client_id
     */
    public static void init(Context context, String client_id) {
        if (npsClient == null)
            npsClient = new NpsClient();
        npsClient.init(context, client_id);
    }

    /**
     *
     * @param user_token
     * @param user_data
     */
    public static void setUser(String user_token, HashMap<String, Object> user_data) {
        if (npsClient == null)
            npsClient = new NpsClient();
        npsClient.setUser(user_token, user_data);
    }

    /**
     *
     * @param surveyCallbacks
     */
    public static void survey(String langauge, SurveyCallbacks surveyCallbacks) {
        if (npsClient == null)
            npsClient = new NpsClient();
        npsClient.survey(langauge != null ? langauge : "en", surveyCallbacks);
    }
}
