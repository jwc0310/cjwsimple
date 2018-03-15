package com.microvirt.cjwsimple.classloader.plugin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.microvirt.cjwsimple.R;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }
}
