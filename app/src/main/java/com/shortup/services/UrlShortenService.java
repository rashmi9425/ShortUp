package com.shortup.services;

import android.support.v4.app.Fragment;

import com.google.gson.JsonObject;
import com.shortup.services.api_interfaces.GetterInterface;
import com.shortup.utils.GlobalConstant;

import retrofit.Retrofit;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.GsonConverterFactory;

public class UrlShortenService {

    private UrlShortenServiceInterface urlShortenServiceInterface;

    private Retrofit retrofit;

    public UrlShortenService(Fragment fragment){
        this.urlShortenServiceInterface = (UrlShortenServiceInterface) fragment;
        this.retrofit = new Retrofit.Builder()
                            .baseUrl(GlobalConstant.base_url)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
    }

    private Callback<JsonObject> responsePojoCallback = new Callback<JsonObject>() {
        @Override
        public void onResponse(Response<JsonObject> response) {
            if (response.isSuccess()){
                urlShortenServiceInterface.onUrlShortenServiceResponseReceived(response.body());
            }else {
                urlShortenServiceInterface.onUrlShortenServiceError("Invalid request !");
            }
        }

        @Override
        public void onFailure(Throwable t) {
            urlShortenServiceInterface.onUrlShortenServiceError("Failure !");
        }
    };

    public void shorten(String longUrl){
        GetterInterface getterInterface = this.retrofit.create(GetterInterface.class);
        Call<JsonObject> responsePojoCall = getterInterface.getShortUrl(GlobalConstant.access_token, longUrl, "json");
        responsePojoCall.enqueue(responsePojoCallback);
    }

    public interface UrlShortenServiceInterface {
        void onUrlShortenServiceResponseReceived(JsonObject responseJson);
        void onUrlShortenServiceError(String message);
    }
}
