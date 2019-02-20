package com.usepace.android.nps.io;

/**
 * Created by MohammedNabil on 12/3/17.
 */

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

class NpsPlatformApiHeaders implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        Request.Builder builder = request.newBuilder()
                .addHeader("Content-Type", "application/json");
        return chain.proceed(builder.build());
    }
}