package com.andy.cjwsimple.detectEmulator;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.framgia.android.emulator.EmulatorDetector;
import com.andy.cjwsimple.BaseAndPermissionsActivity;
import com.andy.cjwsimple.BuildConfig;
import com.andy.cjwsimple.R;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class DetectorEmulator extends BaseAndPermissionsActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detect);

        textView = (TextView) findViewById(R.id.text);
        textView.setText("Checking....");

        PackageManager pm = getPackageManager();
        List<PackageInfo> packageInfos = pm.getInstalledPackages(PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
        for (PackageInfo info : packageInfos)
            Log.e("Andyccc", info.packageName);
        Context context = this;
        File file = new File("/proc/self/cmdline", "r");
        if (file.exists()) {
            Log.e("Andyproc", file.getAbsolutePath());
        } else {
            Log.e("Andyproc", "not exists");
        }


        Class c1 = Context.class;
        try {
            Method m1 = c1.getClass().getDeclaredMethod("getUserId");
            try {
                Log.e("Andy", "adfsd " + m1.invoke(c1));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        // Last check
        // BlueStacksPlayer
        // NoxPlayer
        // KoPlayer
        // MEmu
    }

    List<String> permissions = new ArrayList<>();
    @Override
    public void onResume() {
        super.onResume();
        permissions.add(Manifest.permission.READ_PHONE_STATE);
        requestPermission(permissions, 0x0001);
    }

    @Override
    public void permissionSuccess(int requestCode) {
        super.permissionSuccess(requestCode);
        checkEmulatorDetector();
    }

    @Override
    public void permissionFail(int requestCode) {
        super.permissionFail(requestCode);
        if (requestCode == 0x0001) {

        }
    }

    void checkEmulatorDetector() {
        checkWith(true);
    }

    private void checkWith(boolean telephony) {
        EmulatorDetector.with(this)
            .setCheckTelephony(telephony)
            .addPackageName("com.bluestacks")
            .setDebug(true)
            .detect(new EmulatorDetector.OnEmulatorDetectorListener() {
                @Override
                public void onResult(final boolean isEmulator) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (isEmulator) {
                                textView.setText("This device is emulator"
                                    + getCheckInfo());
                            } else {
                                textView.setText("This device is not emulator"
                                    + getCheckInfo());
                            }
                        }
                    });
                    Log.d(getClass().getName(), "Running on emulator --> " + isEmulator);
                }
            });
    }

    private String getCheckInfo() {
        return "\nTelephony enable is "
            + EmulatorDetector.with(DetectorEmulator.this).isCheckTelephony()
            + "\n\n\n" + EmulatorDetector.getDeviceInfo()
            + "\n\nEmulator Detector version " + BuildConfig.VERSION_NAME;
    }
}
