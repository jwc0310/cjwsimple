package com.microvirt.cjwsimple;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2017/11/22.
 */

public class CjwApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        ActivityThreadHookHelper.doContextStartHook();
    }
}
