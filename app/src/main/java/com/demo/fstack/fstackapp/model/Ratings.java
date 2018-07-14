package com.demo.fstack.fstackapp.model;

import com.google.gson.annotations.SerializedName;

public class Ratings {
    @SerializedName("Source")
    private String source;

    @SerializedName("Value")
    private String value;

    public String getSource() {
        return source;
    }

    public void setSource(String Source) {
        this.source = Source;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ClassPojo [Source = " + source + ", Value = " + value + "]";
    }
}
