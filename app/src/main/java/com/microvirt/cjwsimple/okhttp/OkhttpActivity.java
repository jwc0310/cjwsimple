package com.microvirt.cjwsimple.okhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.microvirt.cjwsimple.R;

import okhttp3.OkHttpClient;

public class OkhttpActivity extends AppCompatActivity {

    private OkHttpClient okHttpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        okHttpClient = new OkHttpClient.Builder()
                .build();

    }
}
