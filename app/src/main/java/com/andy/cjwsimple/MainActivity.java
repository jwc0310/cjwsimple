package com.andy.cjwsimple;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andy.cjwsimple.ads.AdInfo;
import com.andy.cjwsimple.ads.AdvertisingIdClient;
import com.andy.cjwsimple.androidInfo.AndroidInfoActivity;
import com.andy.cjwsimple.asop.GetpropActivity;
import com.andy.cjwsimple.asop.TelephonyActivity;
import com.andy.cjwsimple.base.Services;
import com.andy.cjwsimple.detectEmulator.DetectorEmulator;
import com.andy.cjwsimple.utils.DeviceInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.github.dmitrikudrenko.emulatordetector.Callback;
import io.github.dmitrikudrenko.emulatordetector.EmulatorDetector;
import io.github.dmitrikudrenko.emulatordetector.accelerometer.AccelerometerDetector;
import io.github.dmitrikudrenko.emulatordetector.gyroscope.GyroscopeDetector;

public class MainActivity extends AppCompatActivity {

    private void checkFileExit(String fileOrDir) {
        try {
            File tmpFile = new File(fileOrDir);
            if (tmpFile.exists())
                Log.e("Andy-File", fileOrDir + " is exist");
            else
                Log.e("Andy-File", fileOrDir + " is not exist");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        checkFileExit("/sdcard");
        checkFileExit("/sdcard/Download");
        checkFileExit("/data");
        checkFileExit("/data/data");
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        final AccountManager accountManager = (AccountManager) getSystemService(ACCOUNT_SERVICE);
        Account[] accounts = accountManager.getAccounts();

        UsbManager usbManager = (UsbManager) getSystemService(USB_SERVICE);
        HashMap<String, UsbDevice> deviceList = usbManager.getDeviceList();

        Set<String> sets = deviceList.keySet();
        for (String str : sets) {
            UsbDevice usbDevice = deviceList.get(str);
            Log.e("Andy", usbDevice.toString());
        }

        initGaid();
        findViewById(R.id.jump1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivitySafely(TelephonyActivity.class);
            }
        });

        findViewById(R.id.jump4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initVibrator();
            }
        });
        findViewById(R.id.jump2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivitySafely(InputMethodActivity.class);
                Account[] accounts1 = accountManager.getAccounts();
                for (Account account1 : accounts1) {
                    accountManager.removeAccount(account1, null, null);
                }
            }
        });

        findViewById(R.id.jump3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivitySafely(GetpropActivity.class);
                startActivitySafely(DetectorEmulator.class);

            }
        });

        /*
        LinearLayout rootLayout = (LinearLayout) findViewById(R.id.rootLayout);
        DeviceInfo deviceInfo = new DeviceInfo(getApplicationContext());
        Map<String, String> info = deviceInfo.collectDeviceInfo();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        Map<String, String> deviceInfo2 = deviceInfo.getDeviceInfo();
        List<Map.Entry<String, String>> infoIds2 = new ArrayList<>(deviceInfo2.entrySet());
        Collections.sort(infoIds2, new Comparator<Map.Entry<String, String>>() {
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                //return (o2.getValue() - o1.getValue());
                return (o1.getKey()).compareTo(o2.getKey());
            }
        });

        for (int i = 0; i < infoIds2.size(); i++) {
            Map.Entry<String, String> stringStringEntry = infoIds2.get(i);
            String key = stringStringEntry.getKey();
            String value = stringStringEntry.getValue();
            TextView textView = new TextView(getApplicationContext());
            textView.setText(key + " : " + value);
            textView.setTextColor(0xff000000);
            textView.setLayoutParams(layoutParams);
            rootLayout.addView(textView);
        }


        AccelerometerDetector accelerometerDetector = AccelerometerDetector.builder()
                .setDelay(500)
                .setEventCount(5)
                //check continues 500*5 = 2500ms
                .build();

        GyroscopeDetector gyroscopeDetector = GyroscopeDetector.builder()
                .setDelay(500)
                .setEventCount(5)
                .build();
        EmulatorDetector emulatorDetector = new EmulatorDetector(accelerometerDetector, gyroscopeDetector);
        emulatorDetector.detect(this, new Callback() {
            @Override
            public void onDetect(boolean isEmulator) {
                Log.e("Andy-emulator", isEmulator ? "is emulator" : "is not emulator");
            }

            @Override
            public void onError(Exception exception) {
                Log.e("onError", exception.getMessage(), exception);
            }
        });

        buildInfo();
        */
    }

    private void buildInfo() {
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

        Log.e("Andy", stringBuilder.toString());
    }

    private void initVibrator() {
        Services.VibratorService(this);
        startActivitySafely(AndroidInfoActivity.class);

    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
//    public native String stringFromJNI();

    private static class MainRVViewHolder extends RecyclerView.ViewHolder {
        private TextView item_main;

        public MainRVViewHolder(View itemView) {
            super(itemView);
            item_main = (TextView) itemView.findViewById(R.id.main_rv_item);
        }
    }

    private static class MainRVAdapter extends RecyclerView.Adapter<MainRVViewHolder> {

        private LayoutInflater inflater;
        private List<Class> list;
        private Context context;

        public MainRVAdapter(List<Class> list, Context context) {
            inflater = LayoutInflater.from(context);
            this.list = list;
            this.context = context;
        }

        @Override
        public MainRVViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.item_main_rv, parent, false);
            return new MainRVViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MainRVViewHolder holder, final int position) {
            holder.item_main.setText(list.get(position).getName());
            holder.item_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, list.get(position).getName(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }


    public static AdInfo adInfo;

    private void initGaid() {

        new Thread(new Runnable() {
            public void run() {
                try {
                    adInfo = AdvertisingIdClient
                            .getAdvertisingIdInfo(MainActivity.this);
                    Log.e("Andy-gaid", "adinfo id: " + adInfo.getId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void startActivitySafely(Class dstClass) {
        try {
            Intent intent = new Intent(this, dstClass);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
