package com.microvirt.cjwsimple.detectEmulator;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.framgia.android.emulator.EmulatorDetector;
import com.microvirt.cjwsimple.BaseAndPermissionsActivity;
import com.microvirt.cjwsimple.BuildConfig;
import com.microvirt.cjwsimple.R;

public class DetectorEmulator extends BaseAndPermissionsActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detect);

        textView = (TextView) findViewById(R.id.text);
        textView.setText("Checking....");

        // Last check
        // BlueStacksPlayer
        // NoxPlayer
        // KoPlayer
        // MEmu
    }

    @Override
    public void onResume() {
        super.onResume();
        requestPermission(new String[] {Manifest.permission.READ_PHONE_STATE}, 0x0001);
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
