package com.shortup.services.api_interfaces;

import com.google.gson.JsonObject;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface GetterInterface {

    @GET("v3/shorten")
    Call<JsonObject> getShortUrl(
                        @Query("access_token")      String accessToken,
                        @Query("longUrl")           String longUrl,
                        @Query("format")            String format);

}
