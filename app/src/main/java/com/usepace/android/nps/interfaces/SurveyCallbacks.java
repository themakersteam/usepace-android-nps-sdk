package com.usepace.android.nps.interfaces;

import com.usepace.android.nps.exeptions.NpsException;

public interface SurveyCallbacks {

    void onFailedToLoadSurvey(NpsException npsException);
}
