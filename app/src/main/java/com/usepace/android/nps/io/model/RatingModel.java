package com.usepace.android.nps.io.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class RatingModel implements Serializable {

    @SerializedName("eligible")
    private boolean eligible;
    @SerializedName("question")
    private String question;
    @SerializedName("lowest_hint")
    private String lowest_hint;
    @SerializedName("highest_hint")
    private String highest_hint;
    @SerializedName("dismiss")
    private boolean dismiss;

    /**
     **/
    public void setEligible(boolean eligible) {
        this.eligible = eligible;
    }
    public boolean isEligible() {
        return eligible;
    }

    /**
     **/
    public void setQuestion(String question) {
        this.question = question;
    }
    public String getQuestion() {
        return question;
    }

    /**
     **/
    public void setLowestHint(String lowest_hint) {
        this.lowest_hint = lowest_hint;
    }
    public String getLowestHint() {
        return lowest_hint;
    }

    /**
     **/
    public void setHighestHint(String highest_hint) {
        this.highest_hint = highest_hint;
    }
    public String getHighestHint() {
        return highest_hint;
    }

    /**
     **/
    public void setDismiss(boolean dismiss) {
        this.dismiss = dismiss;
    }
    public boolean isDismiss() {
        return dismiss;
    }

    @Override
    public String toString() {
        return  "eligable:" + eligible + "\n" +
                "question:" + question + "\n" +
                "lowest_hint:" + lowest_hint + "\n" +
                "highest_hint:" + highest_hint + "\n" +
                "dismiss:" + dismiss + "\n";
    }
}
