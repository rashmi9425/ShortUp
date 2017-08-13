package com.shortup.managers.ad_manager;

import android.util.Log;

import com.google.gson.JsonObject;
import com.inmobi.ads.InMobiAdRequestStatus;
import com.inmobi.ads.InMobiNative;

import org.json.JSONObject;

/**
 * Created by rashverm on 8/13/2017.
 */

public class NativeAdListernerImpl implements InMobiNative.NativeAdListener {

    AdManager.AdManagerInterface adManagerInterface;

    public NativeAdListernerImpl(AdManager.AdManagerInterface adManagerInterface) {
        this.adManagerInterface = adManagerInterface;
    }

    @Override
    public void onAdLoadSucceeded(InMobiNative inMobiNative) {
        try {
            JSONObject content = new JSONObject((String) inMobiNative.getAdContent());
            adManagerInterface.onShowAd(content);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onAdLoadFailed(InMobiNative inMobiNative, InMobiAdRequestStatus inMobiAdRequestStatus) {
        adManagerInterface.onRemoveAd();
    }

    @Override
    public void onAdDismissed(InMobiNative inMobiNative) {
        adManagerInterface.onRemoveAd();
    }

    @Override
    public void onAdDisplayed(InMobiNative inMobiNative) {
    }

    @Override
    public void onUserLeftApplication(InMobiNative inMobiNative) {
    }
}
