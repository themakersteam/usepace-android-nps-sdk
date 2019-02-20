package com.usepace.android.nps.exeptions;

public class NpsException extends Exception{

    protected int code;

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public NpsException(String message) {
        super(message);
        this.setCode(0);
    }

    public NpsException(String message, int code) {
        super(message);
        this.setCode(code);
    }

}
