package com.andy.cjwsimple.asop;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.andy.cjwsimple.R;

import java.util.List;

public class SensorActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private TextView textView;
    private SensorEventListener eventListener;
    private Sensor sensor, g_sensor, mSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        textView = (TextView) findViewById(R.id.list);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        StringBuilder stringBuilder = new StringBuilder("");
        for (Sensor sensor : sensorList) {
            stringBuilder.append(sensor.toString());
            stringBuilder.append("\n");
        }
        textView.setText(stringBuilder.toString());
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        g_sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        mSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        eventListener = new SensorEventListener() {

            @Override
            public void onSensorChanged(SensorEvent event) {

                float[] values = event.values;
                Log.i(event.sensor.getName(), "onSensorChanged " + event.sensor.getName() + ", " + values[0] + ", " + values[1] + ", " + values[2]);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                Log.i(sensor.getName(), "onAccuracyChanged " + sensor.getName() + " " + accuracy);
            }
        };


    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(eventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(eventListener, g_sensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(eventListener, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(eventListener);
    }
}
