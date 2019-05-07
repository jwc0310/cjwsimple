package com.andy.cjwsimple.asop;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;

import com.andy.cjwsimple.R;

import java.util.List;

public class InputMethodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_method);


        Log.e("Andy", "version : "+Build.VERSION.SDK_INT);
        Log.e("Andy", "version : "+Build.VERSION.BASE_OS);
        Log.e("Andy", "version : "+Build.VERSION.CODENAME);




        PackageManager packageManager = getPackageManager();
        List<PackageInfo>list = packageManager.getInstalledPackages(0);
        for (PackageInfo info: list)
            Log.e("Andy121313", info.packageName);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        List<InputMethodInfo> inputMethodList = imm.getInputMethodList();
        for (InputMethodInfo inputMethodInfo : inputMethodList) {
//            Log.e("inputmethod", inputMethodInfo.getPackageName());
//            Log.e("inputmethod", inputMethodInfo.getServiceName());
//            Log.e("inputmethod", inputMethodInfo.toString()+"");
        }

        List<InputMethodInfo> inputEnableList = imm.getEnabledInputMethodList();
        for (InputMethodInfo inputMethodInfo2 : inputEnableList) {
            Log.e("inputmethod1", inputMethodInfo2.getPackageName());
            Log.e("inputmethod1", inputMethodInfo2.getServiceName());
            Log.e("inputmethod1", inputMethodInfo2.toString()+"");
        }
    }
}
