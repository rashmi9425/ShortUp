package com.shortup.models.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataPojo {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("hash")
    @Expose
    private String hash;
    @SerializedName("global_hash")
    @Expose
    private String globalHash;
    @SerializedName("long_url")
    @Expose
    private String longUrl;
    @SerializedName("new_hash")
    @Expose
    private Integer newHash;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getGlobalHash() {
        return globalHash;
    }

    public void setGlobalHash(String globalHash) {
        this.globalHash = globalHash;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public Integer getNewHash() {
        return newHash;
    }

    public void setNewHash(Integer newHash) {
        this.newHash = newHash;
    }
}