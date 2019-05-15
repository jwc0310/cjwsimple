package com.andy.cjwsimple.base;

import android.content.Context;
import android.os.Vibrator;
import android.util.Log;

/**
 * Created by Administrator on 2018/5/7.
 */

public class Services {
    private static final String TAG = "Services";

    /**
     * 根据震动服务理解android 系统服务工作流程
     * @param context
     */

    public static void VibratorService(Context context) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

        if (vibrator.hasVibrator()) {
            Log.e(TAG, "has vibrator ");
            long[] args = {1000, 2000, 3000, 4000};
            vibrator.vibrate(1000);
            vibrator.vibrate(args, 1);
        } else {
            Log.e(TAG, "has no vibrator");
        }

    }

}
