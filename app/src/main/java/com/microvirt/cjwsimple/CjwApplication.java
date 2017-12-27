package com.microvirt.cjwsimple;

import android.app.Application;
import android.content.Context;

import com.microvirt.cjwsimple.asop.ApiActivity;
import com.microvirt.cjwsimple.asop.HandlerActivity;
import com.microvirt.cjwsimple.hook.ActivityThreadHookHelper;
import com.microvirt.cjwsimple.hook.HookUtil;

/**
 * Created by Administrator on 2017/11/22.
 */

public class CjwApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //ActivityThreadHookHelper.doContextStartHook();
        HookUtil hookUtil = new HookUtil(HandlerActivity.class, this);
        hookUtil.handleSystemHandler();
        hookUtil.hookAms();
    }
}
