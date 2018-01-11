package com.microvirt.cjwsimple.asop;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.microvirt.cjwsimple.BaseAndPermissionsActivity;
import com.microvirt.cjwsimple.R;
import com.microvirt.cjwsimple.utils.Property;

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
                String text = Property.getHostMacProp();
                showTv.setText(text);
            }
        });
    }
}
