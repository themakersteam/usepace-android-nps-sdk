package com.usepace.android.nps.io;


import com.usepace.android.nps.io.model.Message;
import com.usepace.android.nps.io.model.RatingModel;
import java.util.HashMap;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by MohammedNabil
 */

 interface NpsPlatformApiInterface {

    @GET("eligibleHttp")
    Call<RatingModel> getNpsSurvey(@Header("Authorization") String authorization, @QueryMap(encoded = true) HashMap<String, Object> hashMap,  @Header("Accept-Language") String language);

    @POST("saveResultHttp")
    Call<Message> postNpsSurvey(@Header("Accept-Language") String language, @Header("Authorization") String authorization, @QueryMap(encoded = true) HashMap<String, Object> hashMap, @Body HashMap<String, Object> request);

}
