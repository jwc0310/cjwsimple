package com.andy.cjwsimple.classloader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.andy.cjwsimple.R;

public class ClassLoaderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_loader);
        ClassLoader loader = ClassLoaderActivity.class.getClassLoader();
        while (loader != null) {
            Log.e("classloader", loader.toString());
            loader = loader.getParent();
        }
    }
}
