package com.usepace.android.nps.clients;

import android.content.Context;
import android.content.Intent;
import com.usepace.android.nps.exeptions.NpsException;
import com.usepace.android.nps.interfaces.SurveyCallbacks;
import com.usepace.android.nps.io.NpsPlatformApi;
import com.usepace.android.nps.io.NpsPlatformApiCallbackInterface;
import com.usepace.android.nps.io.model.RatingModel;
import com.usepace.android.nps.screens.rating.RatingActivity;

class NpsClient implements NpsInterface {

    private Context context;
    private String user_token;

    @Override
    public void init(Context context, String user_token) {
        this.context = context;
        this.user_token = user_token;
    }

    @Override
    public void survey(String language, final SurveyCallbacks surveyCallbacks) {
        NpsPlatformApi.Instance().getNpsSurveys(user_token, language, new NpsPlatformApiCallbackInterface<RatingModel>() {
            @Override
            public void onSuccess(RatingModel result) {
                if (context != null) {
                    try {
                        Intent a1 = new Intent(context, RatingActivity.class);
                        a1.putExtra("RATING", result);
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
