package com.andy.cjwsimple.ads;

/**
 * Created by Administrator on 2018/1/24.
 */

public class AdInfo {

    private final String advertisingId;
    private final boolean limitAdTrackingEnabled;

    AdInfo(String advertisingId, boolean limitAdTrackingEnabled) {
        this.advertisingId = advertisingId;
        this.limitAdTrackingEnabled = limitAdTrackingEnabled;
    }

    public String getId() {
        return this.advertisingId;
    }

    public boolean isLimitAdTrackingEnabled() {
        return this.limitAdTrackingEnabled;
    }

}
