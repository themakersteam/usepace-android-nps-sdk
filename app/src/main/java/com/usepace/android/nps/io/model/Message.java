package com.usepace.android.nps.io.model;

import com.google.gson.annotations.SerializedName;

public class Message {

    @SerializedName("message")
    private String message;

    /**
     **/
    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
