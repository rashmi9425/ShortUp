package com.shortup.services.api_interfaces;

import com.shortup.models.pojos.ResponsePojo;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by rashverm on 8/12/2017.
 */

public interface GetterInterface {

    @GET("v3/shorten")
    Call<ResponsePojo> getShortUrl(
                        @Query("access_token")      String accessToken,
                        @Query("longUrl")           String longUrl,
                        @Query("format")            String format);

}
