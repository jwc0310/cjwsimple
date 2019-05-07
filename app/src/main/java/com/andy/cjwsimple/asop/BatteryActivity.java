package com.andy.cjwsimple.asop;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.andy.cjwsimple.R;

public class BatteryActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);
        textView = (TextView) findViewById(R.id.battery_info);
        BatteryManager batteryManager = (BatteryManager)getSystemService(BATTERY_SERVICE);
        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
    }

    @Override
    public void onResume(){
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryReceiver, filter);
    }

    @Override
    public void onPause(){
        super.onPause();
        unregisterReceiver(batteryReceiver);
    }

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            textView.setText(msg.obj.toString());
        }
    };


    private BroadcastReceiver batteryReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.e("BatteryInfo", "battery");
            if (action.equals(Intent.ACTION_BATTERY_CHANGED)) {

                Log.e("BatteryInfo", "battery changed");
                int status = intent.getIntExtra("status", 0);
                int health = intent.getIntExtra("health", 0);
                boolean present = intent.getBooleanExtra("present", false);
                int level = intent.getIntExtra("level", 0);
                int scale = intent.getIntExtra("scale", 0);
                int icon_small = intent.getIntExtra("icon-small", 0);
                int plugged = intent.getIntExtra("plugged", 0);
                int voltage = intent.getIntExtra("voltage", 0);
                int temperature = intent.getIntExtra("temperature", 0);
                String technology = intent.getStringExtra("technology");

                String statusString = "";
                switch (status) {
                    case BatteryManager.BATTERY_STATUS_UNKNOWN:
                        statusString = "unknown";
                        break;
                    case BatteryManager.BATTERY_STATUS_CHARGING:
                        statusString = "charging";
                        break;
                    case BatteryManager.BATTERY_STATUS_DISCHARGING:
                        statusString = "discharging";
                        break;
                    case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                        statusString = "not charging";
                        break;
                    case BatteryManager.BATTERY_STATUS_FULL:
                        statusString = "full";
                        break;
                }

                String healthString = "";
                switch (health) {
                    case BatteryManager.BATTERY_HEALTH_UNKNOWN:
                        healthString = "unknown";
                        break;
                    case BatteryManager.BATTERY_HEALTH_GOOD:
                        healthString = "good";
                        break;
                    case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                        healthString = "overheat";
                        break;
                    case BatteryManager.BATTERY_HEALTH_DEAD:
                        healthString = "dead";
                        break;
                    case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                        healthString = "voltage";
                        break;
                    case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                        healthString = "unspecified failure";
                        break;
                }

                String acString = "";

                switch (plugged) {
                    case BatteryManager.BATTERY_PLUGGED_AC:
                        acString = "plugged ac";
                        break;
                    case BatteryManager.BATTERY_PLUGGED_USB:
                        acString = "plugged usb";
                        break;
                }
                String s = "";
                s = "status: " + statusString + "\n"
                        + "health: " + healthString + "\n"
                        + "present: " + String.valueOf(present) + "\n"
                        + "level: " + String.valueOf(level) + "\n"
                        + "scale: " + String.valueOf(scale) + "\n"
                        + "icon_small: " + String.valueOf(icon_small) + "\n"
                        + "plugged: " + acString + "\n"
                        + "voltage: " + String.valueOf(voltage/100) + "\n"
                        + "temperature: " + String.valueOf(temperature / 10) + "\n"
                        + "technology: " + technology + "\n";
                Message msg = new Message();
                msg.obj = s;
                handler.sendMessage(msg);
            }
        }
    };
}
