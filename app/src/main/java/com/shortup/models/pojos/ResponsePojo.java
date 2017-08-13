package com.shortup.models.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponsePojo {

    @SerializedName("data")
    @Expose
    private DataPojo data;

    public DataPojo getData() {
        return data;
    }

    public void setData(DataPojo data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponsePojo{" +
                "data=" + data +
                '}';
    }
}
