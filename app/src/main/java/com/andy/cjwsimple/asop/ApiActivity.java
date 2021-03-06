package com.andy.cjwsimple.asop;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.andy.cjwsimple.R;

import java.util.List;

public class ApiActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);
        PackageManager packageManager = getPackageManager();

        List<PermissionGroupInfo> list = packageManager.getAllPermissionGroups(PackageManager.GET_META_DATA);
        if (list != null) {
            for (PermissionGroupInfo permissionGroupInfo : list) {
                //Log.e("Andy", permissionGroupInfo.toString());
            }
        }

    }
}
