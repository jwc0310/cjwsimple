package com.microvirt.cjwsimple.binder;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

import com.microvirt.cjwsimple.R;

public class TestBinderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_binder);

        //testBinder();
        ActivityManager activityManager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);

    }

//    private void testBinder() {
//        IBinder iBinder = ServiceManager.getService("SurfaceFlinger");
//    }
}
