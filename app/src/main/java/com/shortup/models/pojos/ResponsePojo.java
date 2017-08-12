package com.shortup.models.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponsePojo {

    @SerializedName("status_code")
    @Expose
    private Integer statusCode;
    @SerializedName("status_txt")
    @Expose
    private String statusTxt;
    @SerializedName("data")
    @Expose
    private DataPojo data;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusTxt() {
        return statusTxt;
    }

    public void setStatusTxt(String statusTxt) {
        this.statusTxt = statusTxt;
    }

    public DataPojo getData() {
        return data;
    }

    public void setData(DataPojo data) {
        this.data = data;
    }
}
