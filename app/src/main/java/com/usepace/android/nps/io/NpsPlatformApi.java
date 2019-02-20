package com.usepace.android.nps.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.usepace.android.nps.io.model.RatingModel;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NpsPlatformApi {


    private String Apilink; /** Api Link**/
    private final long request_time_out = 20;
    private NpsPlatformApiInterface apiInterface;
    private Retrofit retrofit = null;

    private static NpsPlatformApi sendBirdPlatformApi;

    public NpsPlatformApi() {
        Apilink = "https://us-central1-pace-staging-206410.cloudfunctions.net/";
        apiInterface = getClient().create(NpsPlatformApiInterface.class);
    }


    /**
     *
     * @param auth_token
     * @param callback
     */
    public void getNpsSurveys(String auth_token, String langauge,  final NpsPlatformApiCallbackInterface<RatingModel> callback) {
        apiInterface.getNpsSurvey(auth_token, langauge).enqueue(new Callback<RatingModel>() {
            @Override
            public void onResponse(Call<RatingModel> call, Response<RatingModel> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                }
                else {
                    callback.onError("Failed with : " + response.code() + ">> " + response.errorBody() != null ? response.errorBody().toString() : "");
                }
            }

            @Override
            public void onFailure(Call<RatingModel> call, Throwable t) {
                callback.onError("Failed with: " + t != null ? t.getMessage() : "Network !");
            }
        });
    }


    /**
     *
     * @param auth_token
     * @param callback
     */
    public void postNpsSurveys(String auth_token, Integer user_selected_value, boolean dismissed,  final NpsPlatformApiCallbackInterface<ResponseBody> callback) {
        HashMap<String, Object> values = new HashMap<>();
        if (dismissed)
            values.put("dismissed", true);
        else
            values.put("score", user_selected_value);
        apiInterface.postNpsSurvey(auth_token, values).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                }
                else {
                    callback.onError("Failed with : " + response.code() + ">> " + response.errorBody() != null ? response.errorBody().toString() : "");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onError("Failed with: " + t != null ? t.getMessage() : "Network !");
            }
        });
    }

    /**
     *
     * @return RetroFit Client Object For creating Api Requests
     */
    private Retrofit getClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .readTimeout(request_time_out, TimeUnit.SECONDS)
                .writeTimeout(request_time_out, TimeUnit.SECONDS)
                .connectTimeout(request_time_out, TimeUnit.SECONDS);
        client.addInterceptor(new NpsPlatformApiHeaders());
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(Apilink)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client.build())
                .build();
        return retrofit;
    }

    /** Instance for Api **/
    public static NpsPlatformApi Instance() {
        if (sendBirdPlatformApi == null)
            sendBirdPlatformApi = new NpsPlatformApi();
        return sendBirdPlatformApi;
    }

}
