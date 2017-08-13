package com.shortup.controller;

import android.app.Activity;
import android.provider.Settings;
import android.provider.SyncStateContract;
import android.util.Log;

import com.inmobi.ads.InMobiAdRequestStatus;
import com.inmobi.ads.InMobiNative;
import com.shortup.activities.HomeScreen;
import com.shortup.utils.GlobalConstant;
import com.inmobi.ads.InMobiNative;
import org.json.JSONException;
import org.json.JSONObject;

import static com.google.android.gms.wearable.DataMap.TAG;
import static com.shortup.activities.HomeScreen.mApplicationContext;

/**
 * Created by rashverm on 8/12/2017.
 */

public class InMobiNativeAd{
    public static void fetchAd(Activity act,long placementId) {

        InMobiNativeAdListener n1=new InMobiNativeAdListener();
        InMobiNative nativeAd = new InMobiNative(act,placementId,n1);
        nativeAd.load();
    }
}

 class InMobiNativeAdListener implements InMobiNative.NativeAdListener {

    //
   //

    static InMobiNative nativeAd;
    public static InMobiNative getNativeAd(){
    return nativeAd;
    }



    @Override
    public void onAdLoadSucceeded(InMobiNative inMobiNative) {
    Object content=inMobiNative.getAdContent();
        Log.v("On success", content.toString());
    }

    @Override
    public void onAdLoadFailed(InMobiNative inMobiNative, InMobiAdRequestStatus inMobiAdRequestStatus) {
        Log.v("On failure", inMobiAdRequestStatus.getMessage());
    }

    @Override
    public void onAdDismissed(InMobiNative inMobiNative) {

    }

    @Override
    public void onAdDisplayed(InMobiNative inMobiNative) {

    }

    @Override
    public void onUserLeftApplication(InMobiNative inMobiNative) {

    }
}


