package com.shortup.managers.ad_manager;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.inmobi.ads.InMobiNative;
import com.inmobi.sdk.InMobiSdk;
import com.shortup.utils.GlobalConstant;

import org.json.JSONObject;

public class AdManager {

    InMobiNative nativeAd;

    public void init(Activity activity, Fragment fragment){

        InMobiSdk.setLogLevel(InMobiSdk.LogLevel.DEBUG);
        nativeAd = new InMobiNative(activity, GlobalConstant.placementId, new NativeAdListernerImpl((AdManagerInterface) fragment));
        fetchAd();
    }

    public void fetchAd(){
        if (nativeAd != null)
            nativeAd.load();
    }

    // Fragment needs to implement this interface
    public interface AdManagerInterface{
        public void onShowAd(JSONObject content);
        public void onRemoveAd();
    }

}
