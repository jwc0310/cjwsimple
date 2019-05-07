package com.andy.cjwsimple;

import android.app.Application;
import android.content.Context;

import com.andy.cjwsimple.asop.ApiActivity;
import com.andy.cjwsimple.asop.HandlerActivity;
import com.andy.cjwsimple.hook.ActivityThreadHookHelper;
import com.andy.cjwsimple.hook.HookUtil;

/**
 * Created by Administrator on 2017/11/22.
 */

public class CjwApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //ActivityThreadHookHelper.doContextStartHook();
        HookUtil hookUtil = new HookUtil(HandlerActivity.class, this);
//        hookUtil.handleSystemHandler();
        hookUtil.hookAms();
        //hookUtil.handleBuildinfo();
        //hookUtil.handleOpenGLInfo();
    }
}
