package com.andy.cjwsimple.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by Administrator on 2018/3/20.
 */

public class Permission {

    //权限检查
    public boolean checkSelPermission(Context context, String permission) {
        //PackageManager.PERMISSION_DENIED
        //PackageManager.PERMISSION_GRANTED;
        int result = ContextCompat.checkSelfPermission(context, permission);
        if (result == PackageManager.PERMISSION_GRANTED)
            return true;
        else
            return false;
    }

    //解释权限
    public boolean explainPermission(Activity activity, String permission) {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
    }

    //请求权限
    public void requestPermission(Activity activity, String[] permissions, int requestCode) {
        ActivityCompat.requestPermissions(activity, permissions, requestCode);
    }

}
