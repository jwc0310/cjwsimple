package com.microvirt.cjwsimple;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
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

import com.microvirt.cjwsimple.asop.BatteryActivity;
import com.microvirt.cjwsimple.classloader.ClassLoaderActivity;
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

    private RecyclerView main_rv;
    private List<Class> list;
    private MainRVAdapter adapter;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

        }
    };

    private SensorEventListener eventListener;
    private Sensor mSensor;
    private SensorManager mSensorManager;

    @Override
    public void onResume() {
        super.onResume();
        //mSensorManager.registerListener(eventListener, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        //mSensorManager.unregisterListener(eventListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.jump1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivitySafely(BatteryActivity.class);
            }
        });

        Log.e("Andy", new MyJni().stringFromJNI());


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
//
//
//        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
//        List<Sensor> sensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);
////        for (Sensor sensor : sensorList){
////            Log.e("Andy", sensor.toString());
////        }
//
//        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        eventListener = new SensorEventListener() {
//
//            @Override
//            public void onSensorChanged(SensorEvent event) {
//                float[] values = event.values;
//                //Log.e("Andy", event.sensor.getName()+", "+values[0]+", "+values[1]+", "+values[2]);
//            }
//
//            @Override
//            public void onAccuracyChanged(Sensor sensor, int accuracy) {
//                //Log.e("Andy", sensor.getName()+" "+accuracy);
//            }
//        };
//
//
//        File dataDir = Environment.getDataDirectory();
//        Log.e("Andy", "dataDir = " + dataDir.getAbsolutePath());
//        File externalStorageDirectory = Environment.getExternalStorageDirectory();
//        Log.e("Andy", "externalStorageDirectory = " + externalStorageDirectory.getAbsolutePath());
//
//        File rootDir = Environment.getRootDirectory();
//        Log.e("Andy", "rootDir = " + rootDir.getAbsolutePath());
//
//        File downloadCacheDirectory = Environment.getDownloadCacheDirectory();
//        Log.e("Andy", "downloadCacheDirectory = " + downloadCacheDirectory.getAbsolutePath());
//
//        Log.e("Andy", Build.VERSION.SDK+" "+Build.VERSION.RELEASE);
//

//        list = new ArrayList<>();
//        adapter = new MainRVAdapter(list, this);
//        main_rv = (RecyclerView) findViewById(R.id.main_rv);
//        main_rv.setLayoutManager(new LinearLayoutManager(this));
//        main_rv.setAdapter(adapter);
//
//        initData();
//        ActivityThreadHookHelper.doActivityStartHook(this);
//        startActivity(new Intent(this, HandlerActivity.class));


    }

    private void initData() {
        File file = new File(".");
        Log.e("Andy", file.getName() + ", absolute path:" + file.getAbsolutePath());
        if (file.isDirectory()) {
            for (File tmp : file.listFiles()) {
                if (tmp.getName().endsWith("Activity")) {
                    list.add(tmp.getClass());
                }
            }
        }
        adapter.notifyDataSetChanged();
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
