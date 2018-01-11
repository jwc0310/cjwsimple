package com.microvirt.cjwsimple.asop;

import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.microvirt.cjwsimple.BaseAndPermissionsActivity;
import com.microvirt.cjwsimple.R;

public class TelephonyActivity extends BaseAndPermissionsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telephony);

        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        Log.e("Andy1", telephonyManager.getDeviceId()+"");
        Log.e("Andy1", telephonyManager.getSimSerialNumber()+"");
        Log.e("Andy1", telephonyManager.getSubscriberId()+"");
        Log.e("Andy1", telephonyManager.getLine1Number()+"");

    }
}
