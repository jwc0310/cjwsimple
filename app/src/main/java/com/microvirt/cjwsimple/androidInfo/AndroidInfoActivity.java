package com.microvirt.cjwsimple.androidInfo;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.microvirt.cjwsimple.R;

public class AndroidInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_info);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Build.product :");
        stringBuilder.append(Build.PRODUCT);
        stringBuilder.append("\n");

        stringBuilder.append("Build.MANUFACTURER :");
        stringBuilder.append(Build.MANUFACTURER);
        stringBuilder.append("\n");

        stringBuilder.append("Build.BRAND :");
        stringBuilder.append(Build.BRAND);
        stringBuilder.append("\n");

        stringBuilder.append("Build.DEVICE :");
        stringBuilder.append(Build.DEVICE);
        stringBuilder.append("\n");

        stringBuilder.append("Build.MODEL :");
        stringBuilder.append(Build.MODEL);
        stringBuilder.append("\n");

        stringBuilder.append("Build.Hardware :");
        stringBuilder.append(Build.HARDWARE);
        stringBuilder.append("\n");

        stringBuilder.append("Build.fingerprint :");
        stringBuilder.append(Build.FINGERPRINT);
        stringBuilder.append("\n");

        stringBuilder.append("Build.serial :");
        stringBuilder.append(Build.SERIAL);
        stringBuilder.append("\n");

        ((TextView)findViewById(R.id.content)).setText(stringBuilder.toString());

    }
}
