package com.shortup.managers.ad_manager;

import com.inmobi.ads.InMobiAdRequestStatus;
import com.inmobi.ads.InMobiNative;

public interface NativeAdListener {
    /**
     * Called to notify that an ad was successfully fetched.
     *
     * @param ad
     */
    void onAdLoadSucceeded(InMobiNative inMobiNative);
    /**
     * Called to notify that a request to fetch an ad failed.
     *
     * @param ad
     * @param status
     */
    void onAdLoadFailed(InMobiNative inMobiNative, InMobiAdRequestStatus status);
    /**
     * Called to notify that a user is about to return to the application after
     * clicking on the ad.
     *
     * @param ad
     */
    void onAdDismissed(InMobiNative inMobiNative);
    /**
     * Called to notify that the ad opened an overlay that covers the screen.
     *
     * @param ad
     */
    void onAdDisplayed(InMobiNative inMobiNative);
    /**
     * Called to notify that the user is about to leave the application as a result
     * of interacting with it.
     *
     * @param ad
     */
    void onUserLeftApplication(InMobiNative inMobiNative);
}
