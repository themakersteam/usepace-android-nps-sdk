package com.usepace.android.nps.clients;

import android.content.Context;
import android.content.Intent;
import com.usepace.android.nps.exeptions.NpsException;
import com.usepace.android.nps.interfaces.SurveyCallbacks;
import com.usepace.android.nps.io.NpsPlatformApi;
import com.usepace.android.nps.io.NpsPlatformApiCallbackInterface;
import com.usepace.android.nps.io.model.RatingModel;
import com.usepace.android.nps.screens.rating.view.RatingActivity;
import java.util.HashMap;

class NpsClient implements NpsInterface {

    private Context context;
    private String user_token;
    private String client_id;
    private HashMap<String, Object> user_data;

    @Override
    public void init(Context context, String client_id) {
        this.context = context;
        this.client_id = client_id;
    }

    @Override
    public void setUser(String user_token, HashMap<String, Object> user_data) {
        this.user_token = user_token;
        this.user_data = user_data;
    }

    @Override
    public void survey(String language, final SurveyCallbacks surveyCallbacks) {
        NpsPlatformApi.Instance().getNpsSurveys(user_token, client_id,  language, new NpsPlatformApiCallbackInterface<RatingModel>() {
            @Override
            public void onSuccess(RatingModel result) {
                if (context != null) {
                    try {
                        Intent a1 = new Intent(context, RatingActivity.class);
                        a1.putExtra("RATING", result);
                        a1.putExtra("CLIENT_ID", client_id);
                        a1.putExtra("USER_TOKEN", user_token);
                        context.startActivity(a1);
                    }
                    catch (Exception e){
                        if (surveyCallbacks != null) {
                            surveyCallbacks.onFailedToLoadSurvey(new NpsException("App is destroyed"));
                        }
                    }
                }
            }
            @Override
            public void onError(String error) {
                if (surveyCallbacks != null) {
                    surveyCallbacks.onFailedToLoadSurvey(new NpsException(error));
                }
            }
        });
    }

}
