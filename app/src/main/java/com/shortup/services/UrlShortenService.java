package com.shortup.services;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.util.Log;

import com.google.gson.GsonBuilder;
import com.shortup.models.pojos.ResponsePojo;
import com.shortup.services.api_interfaces.GetterInterface;
import com.shortup.utils.GlobalConstant;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit.Retrofit;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class UrlShortenService {

    private ResponsePojo responsePojo;
    private Context context;
    private UrlShortenServiceInterface urlShortenServiceInterface;

    private static Retrofit retrofit=null;

    public UrlShortenService(Context context, Fragment fragment){
        this.context = context;
        this.urlShortenServiceInterface = (UrlShortenServiceInterface) fragment;
        this.responsePojo = new ResponsePojo();

        this.retrofit = new Retrofit.Builder()
                            .baseUrl(GlobalConstant.base_url).addConverterFactory(ScalarsConverterFactory.create()).build();
                            //.addConverterFactory(GsonConverterFactory.create())
                            //.build();
    }

   /* private Callback<ResponsePojo> responsePojoCallback = new Callback<ResponsePojo>() {
        @Override
        public void onResponse(Response<ResponsePojo> response) {
            if (response.isSuccess()){
                responsePojo = response.body();
                urlShortenServiceInterface.onUrlShortenServiceResponseReceived(responsePojo);
            }else {
                urlShortenServiceInterface.onUrlShortenServiceError("Invalid request !");
            }
        }*/

    private Callback<String> responsePojoCallback = new Callback<String>() {
        @Override
        public void onResponse(Response<String> response) {
            Log.v("Inside Callback",response.body());
            if (response.isSuccess()){
                ResponsePojo res = new GsonBuilder().create().fromJson(response.body(),ResponsePojo.class);
                urlShortenServiceInterface.onUrlShortenServiceResponseReceived(res);
            }else {
                urlShortenServiceInterface.onUrlShortenServiceError("Invalid request !");
            }
        }


    @Override
        public void onFailure(Throwable t) {
            Log.v(">>>>>>>>>>>>>>>>>>>", t.toString());
            urlShortenServiceInterface.onUrlShortenServiceError("Failure !");
        }
    };

    public void shorten(String longUrl){
        GetterInterface getterInterface = this.retrofit.create(GetterInterface.class);
        Call<String> responsePojoCall = getterInterface.getShortUrl(GlobalConstant.access_token, longUrl, "json");
        responsePojoCall.enqueue(responsePojoCallback);
    }

    public interface UrlShortenServiceInterface {
        void onUrlShortenServiceResponseReceived(ResponsePojo responsePojo);
        void onUrlShortenServiceError(String message);
    }
}
