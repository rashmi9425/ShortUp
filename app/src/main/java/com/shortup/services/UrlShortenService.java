package com.shortup.services;

import android.support.v4.app.Fragment;
import android.content.Context;

import com.shortup.models.pojos.ResponsePojo;
import com.shortup.services.api_interfaces.GetterInterface;
import com.shortup.utils.GlobalConstant;

import retrofit2.Retrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UrlShortenService {

    private ResponsePojo responsePojo;
    private Context context;
    private UrlShortenServiceInterface urlShortenServiceInterface;

    private Retrofit retrofit;

    public UrlShortenService(Context context, Fragment fragment){
        this.context = context;
        this.urlShortenServiceInterface = (UrlShortenServiceInterface) fragment;
        this.responsePojo = new ResponsePojo();
    }

    private Callback<ResponsePojo> responsePojoCallback =
            new Callback<ResponsePojo>() {
                @Override
                public void onResponse(Call<ResponsePojo> call, Response<ResponsePojo> response) {
                    if (response.isSuccessful()){
                        responsePojo = response.body();
                        urlShortenServiceInterface.onUrlShortenServiceResponseReceived(responsePojo);
                    }else {
                        urlShortenServiceInterface.onUrlShortenServiceError("Invalid request !");
                    }
                }

                @Override
                public void onFailure(Call<ResponsePojo> call, Throwable t) {
                    urlShortenServiceInterface.onUrlShortenServiceError("Unable to reach server !");
                }
            };

    public void shorten(String longUrl){
        GetterInterface getterInterface = this.retrofit.create(GetterInterface.class);
        Call<ResponsePojo> responsePojoCall = getterInterface.getShortUrl(GlobalConstant.access_token, longUrl, "json");
        responsePojoCall.enqueue(responsePojoCallback);
    }

    public interface UrlShortenServiceInterface {
        void onUrlShortenServiceResponseReceived(ResponsePojo responsePojo);
        void onUrlShortenServiceError(String message);
    }
}
