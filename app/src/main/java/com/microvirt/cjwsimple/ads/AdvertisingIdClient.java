package com.microvirt.cjwsimple.ads;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Looper;
import android.util.Log;

/**
 * Created by Administrator on 2017/9/18.
 */

public class AdvertisingIdClient {

    public static String resetGaid(Context context) throws Exception {
        String id = "";
        try {
            PackageManager pm = context.getPackageManager();
            pm.getPackageInfo("com.android.vending", 0);
        } catch (Exception e) {
            //e.printStackTrace();
            Log.e("Andy", "exception");
        }

        AdvertisingConnection connection = new AdvertisingConnection();
        Intent intent = new Intent(
                "com.google.android.gms.ads.identifier.service.START");
        intent.setPackage("com.google.android.gms");
        if (context.bindService(intent, connection, Context.BIND_AUTO_CREATE)) {
            try {
                AdvertisingInterface adInterface = new AdvertisingInterface(
                        connection.getBinder());
                id = adInterface.resetGaid();
            } catch (Exception exception) {
                exception.printStackTrace();
            } finally {
                context.unbindService(connection);
            }
        }
        return id;
    }

    public static AdInfo getAdvertisingIdInfo(Context context) throws Exception {
        AdInfo adInfo = null;
        if (Looper.myLooper() == Looper.getMainLooper())
            throw new IllegalStateException(
                    "Cannot be called from the main thread");

        try {
            PackageManager pm = context.getPackageManager();
            pm.getPackageInfo("com.android.vending", 0);
        } catch (Exception e) {
            //e.printStackTrace();
            return new AdInfo("-1", false);
        }

        AdvertisingConnection connection = new AdvertisingConnection();
        Intent intent = new Intent(
                "com.google.android.gms.ads.identifier.service.START");
        intent.setPackage("com.google.android.gms");
        if (context.bindService(intent, connection, Context.BIND_AUTO_CREATE)) {
            try {
                AdvertisingInterface adInterface = new AdvertisingInterface(
                        connection.getBinder());
                adInfo = new AdInfo(adInterface.getId(),
                        adInterface.isLimitAdTrackingEnabled(true));
                return adInfo;
            } catch (Exception exception) {
                exception.printStackTrace();
            } finally {
                context.unbindService(connection);
            }
        }
        return adInfo;
    }
}
