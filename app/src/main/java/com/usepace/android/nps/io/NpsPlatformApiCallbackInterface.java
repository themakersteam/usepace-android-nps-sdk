package com.usepace.android.nps.io;

/**
 * Created by Mohammed Nabil on 4/3/17.
 */

public interface NpsPlatformApiCallbackInterface<T> {

    void onSuccess(T result);
    void onError(String error);

}