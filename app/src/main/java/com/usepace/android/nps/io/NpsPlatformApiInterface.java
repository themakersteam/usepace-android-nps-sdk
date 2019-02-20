package com.usepace.android.nps.io;


import com.google.gson.JsonElement;
import java.util.HashMap;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by MohammedNabil
 */

 interface NpsPlatformApiInterface {

    @GET("eligibleHttp")
    Call<JsonElement> getNpsSurvey(@Header("Authorization") String authorization);

    @POST("saveResultHttp")
    Call<ResponseBody> postNpsSurvey(@Header("Authorization") String authorization, @Body HashMap<String, Object> request);

}
