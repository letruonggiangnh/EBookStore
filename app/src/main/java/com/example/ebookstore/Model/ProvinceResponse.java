package com.example.ebookstore.Model;

import com.google.gson.annotations.SerializedName;

public class ProvinceResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private City[] data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public City[] getData() {
        return data;
    }

    public void setData(City[] data) {
        this.data = data;
    }
}
