package com.andy.cjwsimple.asop;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.andy.cjwsimple.BaseAndPermissionsActivity;
import com.andy.cjwsimple.R;
import com.andy.cjwsimple.utils.Property;

public class GetpropActivity extends BaseAndPermissionsActivity {

    private Button getBtn;
    private TextView showTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getprop);
        getBtn = (Button) findViewById(R.id.get_prop);
        showTv = (TextView) findViewById(R.id.prop_content);
        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "";
                showTv.setText(text);
            }
        });
    }
}
