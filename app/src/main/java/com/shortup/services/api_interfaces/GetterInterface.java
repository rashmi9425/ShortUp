package com.shortup.services.api_interfaces;

import com.google.gson.JsonObject;
import com.shortup.models.pojos.ResponsePojo;
import com.squareup.okhttp.ResponseBody;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by rashverm on 8/12/2017.
 */

public interface GetterInterface {

    @GET("v3/shorten")
    Call<JsonObject> getShortUrl(
                        @Query("access_token")      String accessToken,
                        @Query("longUrl")           String longUrl,
                        @Query("format")            String format);

}
