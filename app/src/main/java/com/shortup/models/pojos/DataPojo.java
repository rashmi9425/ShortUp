package com.shortup.models.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataPojo {

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("long_url")
    @Expose
    private String longUrl;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    @Override
    public String toString() {
        return "DataPojo{" +
                "url='" + url + '\'' +
                ", longUrl='" + longUrl + '\'' +
                '}';
    }
}