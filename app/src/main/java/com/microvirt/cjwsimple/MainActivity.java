package com.microvirt.cjwsimple;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.microvirt.cjwsimple.asop.SensorActivity;
import com.microvirt.cjwsimple.detectEmulator.DetectorEmulator;
import com.microvirt.cjwsimple.jni.MyJni;
import com.microvirt.cjwsimple.utils.DeviceInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private void startActivitySafely(Class dstClass) {
        try {
            Intent intent = new Intent(this, dstClass);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        findViewById(R.id.jump1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivitySafely(DetectorEmulator.class);
            }
        });

        Log.e("Andy", new MyJni().stringFromJNI());
        Log.e("Andy", Build.MODEL);


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
            Log.e("ybshuai", "key = " + key + " value = " + value);
            textView.setText(key + " : " + value);
            textView.setTextColor(0xff000000);
            textView.setLayoutParams(layoutParams);
            rootLayout.addView(textView);
        }
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
}
