package com.microvirt.cjwsimple.asop;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.telephony.CellInfo;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import android.widget.TextView;

import com.microvirt.cjwsimple.BaseAndPermissionsActivity;
import com.microvirt.cjwsimple.R;

import java.util.ArrayList;
import java.util.List;

public class TelephonyActivity extends BaseAndPermissionsActivity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telephony);

        textView = (TextView) findViewById(R.id.telephony_text);
        List<String> permissions = new ArrayList<>();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.READ_PHONE_STATE);
        }

        if (permissions.size() > 0) {
            requestPermission(permissions, 0x000);
            return;
        }
        showMessage();

    }

    @Override
    public void permissionSuccess(int requestCode) {
        showMessage();
    }

    private void showMessage() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        StringBuilder stringBuilder = new StringBuilder("");

        stringBuilder.append("mccmnc = ");
        stringBuilder.append(telephonyManager.getNetworkOperator());
        stringBuilder.append("\n");

        List<NeighboringCellInfo> neighboringCellInfoList = telephonyManager.getNeighboringCellInfo();
        for (NeighboringCellInfo neighboringCellInfo : neighboringCellInfoList) {
            stringBuilder.append("lac : ");
            stringBuilder.append(neighboringCellInfo.getLac());
            stringBuilder.append("cid : ");
            stringBuilder.append(neighboringCellInfo.getCid());
            stringBuilder.append("BSSS : ");
            stringBuilder.append(-133 + 2 * neighboringCellInfo.getRssi());
            stringBuilder.append("PSC : ");
            stringBuilder.append(neighboringCellInfo.getPsc());
            stringBuilder.append("\n");
        }
        try {
            GsmCellLocation gsmCellLocation = (GsmCellLocation) telephonyManager.getCellLocation();
            stringBuilder.append("gsm loc : ");
            int lac = gsmCellLocation.getLac();
            stringBuilder.append(lac);
            stringBuilder.append("\n");
            stringBuilder.append("gsm cid : ");
            stringBuilder.append(gsmCellLocation.getCid());
            stringBuilder.append("\n");
        }catch (Exception e) {
            e.printStackTrace();
        }

        stringBuilder.append("deviceId = ");
        stringBuilder.append(telephonyManager.getDeviceId());
        stringBuilder.append("\n");

        stringBuilder.append("serialNumber = ");
        stringBuilder.append(telephonyManager.getSimSerialNumber());
        stringBuilder.append("\n");

        stringBuilder.append("subscriberId = ");
        stringBuilder.append(telephonyManager.getSubscriberId());
        stringBuilder.append("\n");

        stringBuilder.append("lineNumber = ");
        stringBuilder.append(telephonyManager.getLine1Number());
        stringBuilder.append("\n");

        textView.setText(stringBuilder.toString());
    }
}
