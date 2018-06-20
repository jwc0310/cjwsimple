package com.microvirt.cjwsimple.utils;

import android.Manifest;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL10;

public class DeviceInfo {
    private static String TAG = "DeviceInfo";
    private Context ctx = null;
    private Map<String, String> info = null;
    EGL10 mEGL;
    EGLConfig mEGLConfig;
    EGLConfig[] mEGLConfigs;
    EGLContext mEGLContext;
    EGLDisplay mEGLDisplay;
    EGLSurface mEGLSurface;
    GL10 mGL;

    public DeviceInfo(Context context) {
        this.ctx = context;
    }

    private EGLConfig chooseConfig() {
        int[] arrayOfInt1 = new int[13];
        arrayOfInt1[0] = 12325;
        arrayOfInt1[2] = 12326;
        arrayOfInt1[4] = 12324;
        arrayOfInt1[5] = 8;
        arrayOfInt1[6] = 12323;
        arrayOfInt1[7] = 8;
        arrayOfInt1[8] = 12322;
        arrayOfInt1[9] = 8;
        arrayOfInt1[10] = 12321;
        arrayOfInt1[11] = 8;
        arrayOfInt1[12] = 12344;
        int[] arrayOfInt2 = new int[1];
        this.mEGL.eglChooseConfig(this.mEGLDisplay, arrayOfInt1, null, 0, arrayOfInt2);
        int i = arrayOfInt2[0];
        this.mEGLConfigs = new EGLConfig[i];
        this.mEGL.eglChooseConfig(this.mEGLDisplay, arrayOfInt1, this.mEGLConfigs, i, arrayOfInt2);
        return this.mEGLConfigs[0];
    }

    private void getBundleVersion() {
        String str3 = this.ctx.getPackageName();
        if (str3 != null) {
        }
        PackageInfo localPackageInfo = null;
        try {
            localPackageInfo = this.ctx.getPackageManager().getPackageInfo(this.ctx.getPackageName(), 1);
        } catch (NameNotFoundException e) {
            String str2 = "unknown";
        }
        String str1 = "";
        if (localPackageInfo != null) {
            if (localPackageInfo.versionName == null) {
                str1 = "unknown";
            } else {
                str1 = localPackageInfo.versionName;
            }
        }
        str1 = str3 + "_" + str1;
        this.info.put("bundle_version", str1);
        str3 = "unknown";
        return;
    }

    private boolean getDeviceBasicInfo() {
        this.info.put("model", Build.MODEL);
        this.info.put("brand", Build.BRAND);
        Log.e("Andy666", Build.BOARD);
        Log.e("Andy666", Build.PRODUCT);
        Log.e("Andy666", String.valueOf(VERSION.SDK_INT));


        this.info.put("mfr", Build.MANUFACTURER);
        this.info.put("board", Build.BOARD);
        this.info.put("CPU_ABI", Build.CPU_ABI);
        this.info.put("CPU_ABI2", Build.CPU_ABI2);
        ((ActivityManager) this.ctx.getSystemService("activity")).getMemoryInfo(new MemoryInfo());
        this.info.put("total_mem", Formatter.formatFileSize(this.ctx, getTotalMemory()));
        Object localObject1 = new StatFs(Environment.getDataDirectory().getAbsolutePath());
        long l = ((StatFs) localObject1).getBlockSize();
        localObject1 = Formatter.formatFileSize(this.ctx, ((StatFs) localObject1).getBlockCount() * l);
        this.info.put("in_size", localObject1.toString());
        Iterator localIterator = null;
        if ("mounted".equals(Environment.getExternalStorageState())) {
            localObject1 = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
            l = ((StatFs) localObject1).getBlockSize();
            localObject1 = Formatter.formatFileSize(this.ctx, ((StatFs) localObject1).getBlockCount() * l);
            this.info.put("ex_size", localObject1.toString());
            this.info.put("with_sd_card", "true");
            boolean bool = isRooted();
            this.info.put("is_rooted", String.valueOf(bool));
            localObject1 = (ArrayList) getCpuInfo();
            localIterator = ((ArrayList) localObject1).iterator();
        }
        for (; ; ) {
            if (!localIterator.hasNext()) {
                if (((ArrayList) localObject1).size() > 0) {
                    String[] localObject2 = ((String) ((ArrayList) localObject1).get(0)).split(":");
                    if (localObject2.length > 0) {
                        this.info.put("CPU", localObject2[1]);
                    }
                }
                localObject1 = new DisplayMetrics();
                ((WindowManager) this.ctx.getSystemService("window")).getDefaultDisplay().getMetrics((DisplayMetrics) localObject1);
                int i = ((DisplayMetrics) localObject1).widthPixels;
                int j = ((DisplayMetrics) localObject1).heightPixels;
                this.info.put("screen_width", String.valueOf(i));
                this.info.put("screen_height", String.valueOf(j));
                this.info.put("with_sd_card", "false");
                this.info.put("xdpi", ""+((DisplayMetrics) localObject1).xdpi);
                this.info.put("ydpi", ""+((DisplayMetrics) localObject1).ydpi);
                return true;
            }
            Object localObject2 = (String) localIterator.next();
            if (((String) localObject2).contains("Hardware")) {
                String[] localObject3 = ((String) localObject2).split(":");
                if (localObject3.length >= 2) {
                    this.info.put("Hardware", localObject3[1]);
                }
            }
        }
    }

    private boolean getDeviceCrashInfo() {
            try {
                this.info.put("rls_version", VERSION.RELEASE);
                this.info.put("sdk_version", String.valueOf(VERSION.SDK_INT));
                Object localObject1 = (ActivityManager) this.ctx.getSystemService("activity");
                Object localObject2 = new MemoryInfo();
                ((ActivityManager) localObject1).getMemoryInfo((MemoryInfo) localObject2);
                this.info.put("avl_mem", Formatter.formatFileSize(this.ctx, ((MemoryInfo) localObject2).availMem));
                this.info.put("threshold_mem", Formatter.formatFileSize(this.ctx, ((MemoryInfo) localObject2).threshold));
                this.info.put("is_low_mem", String.valueOf(((MemoryInfo) localObject2).lowMemory));
                long l;
                if (Environment.getDataDirectory() != null) {
                    localObject1 = new StatFs(Environment.getDataDirectory().getAbsolutePath());
                    l = ((StatFs) localObject1).getBlockSize();
                    localObject1 = Formatter.formatFileSize(this.ctx, ((StatFs) localObject1).getAvailableBlocks() * l);
                    this.info.put("in_avl_size", localObject1.toString());
                }
                if (("mounted".equals(Environment.getExternalStorageState())) && (Environment.getExternalStorageDirectory() != null)) {
                    localObject1 = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
                    l = ((StatFs) localObject1).getBlockSize();
                    localObject1 = Formatter.formatFileSize(this.ctx, ((StatFs) localObject1).getAvailableBlocks() * l);
                    this.info.put("ex_avl_size", localObject1.toString());
                }
                localObject2 = this.ctx.getResources().getConfiguration();
                localObject1 = "unknow";
                int i = ((Configuration) localObject2).orientation;
                if (i != 2) {

                }
                localObject1 = "LANDSCAPE";
                this.info.put("ori", localObject1.toString());
                localObject1 = this.ctx.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
                i = ((Intent) localObject1).getIntExtra("status", 0);
                int j = ((Intent) localObject1).getIntExtra("health", 1);
                boolean bool = ((Intent) localObject1).getBooleanExtra("present", false);
                ((Intent) localObject1).getIntExtra("plugged", 0);
                double d = ((Intent) localObject1).getIntExtra("temperature", 0) / 10.0D;
                String[] localObject3 = new String[8];
                localObject3[0] = "NULL";
                localObject3[1] = "UNKNOWN";
                localObject3[2] = "GOOD";
                localObject3[3] = "OVERHEAT";
                localObject3[4] = "DEAD";
                localObject3[5] = "OVER_VOLTAGE";
                localObject3[6] = "UNSPECIFIED_FAILURE";
                localObject3[7] = "COLD";
                String[] localObject4 = new String[6];
                localObject4[0] = "NULL";
                localObject4[1] = "UNKNOWN";
                localObject4[2] = "CHARGING";
                localObject4[3] = "DISCHARGING";
                localObject4[4] = "NOT_CHARGING";
                localObject4[5] = "FULL";
                if ((i < localObject4.length) && (i >= 0)) {
                    this.info.put("Battery_State", localObject4[i]);
                }
                if ((j < localObject3.length) && (j >= 0)) {
                    this.info.put("Battery_Health", localObject3[j]);
                }
                this.info.put("Is_Battery_Present", String.valueOf(bool));
                this.info.put("Battery_Temperature", String.valueOf(d));
                localObject1 = ((ConnectivityManager) this.ctx.getSystemService("connectivity")).getActiveNetworkInfo();
                if (localObject1 != null) {
                    localObject2 = ((NetworkInfo) localObject1).getDetailedState();
                    ((NetworkInfo) localObject1).getTypeName();
                    this.info.put("net_state", String.valueOf(localObject2));
                    if (((NetworkInfo) localObject1).getType() == 1) {
                        this.info.put("net_type", "WIFI");
                    } else if (((NetworkInfo) localObject1).getType() == 0) {
                        this.info.put("net_type", "radio");
                        this.info.put("net_pto", ((NetworkInfo) localObject1).getSubtypeName());
                    } else {
                        this.info.put("net_type", "Unknown");
                    }
                } else {
                    this.info.put("net_state", "Not_Available");
                    this.info.put("net_type", "Disconnected");
                }
                if (i == 1) {
                    localObject1 = "PORTRAIT";
                }
            } catch (Exception localException) {
                Object localObject1;
                int i;
            }
            return true;
    }

    private void getDeviceMD5() {
        for (; ; ) {
            try {
                Object localObject3 = (TelephonyManager) this.ctx.getSystemService("phone");
                Object localObject1 = "";
                Object localObject2;
                try {
                    if (ActivityCompat.checkSelfPermission(ctx, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    localObject2 = ((TelephonyManager) localObject3).getDeviceId();
                    localObject1 = localObject2;
                } catch (Exception localException2) {
                    String str5;
                    String str4;
                    String str3;
                    continue;
                }
                if (ActivityCompat.checkSelfPermission(ctx, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                String str5 = ((TelephonyManager) localObject3).getSimSerialNumber();
                String str4 = Build.SERIAL;
                String str3 = Settings.Secure.getString(this.ctx.getContentResolver(), "android_id");
                localObject3 = new StringBuilder();
                if (localObject1 != null) {
                    localObject2 = localObject1;
                    ((StringBuilder) localObject3).append((String) localObject2);
                    if (str5 == null) {
                        String str2 = "null";
                        break;
                    }
                    localObject2 = localObject1;
                    ((StringBuilder) localObject3).append((String) localObject2);
                    if (str4 == null) {
                        String str2 = "null";
                        break;
                    }
                    localObject2 = localObject1;
                    ((StringBuilder) localObject3).append((String) localObject2);
                    if (str3 == null) {
                        String str1 = "null";
                        break;
                    }
                    ((StringBuilder) localObject3).append((String) localObject1);
                    localObject1 = ((StringBuilder) localObject3).toString();
//                    localObject1 = AndroidCrashHandler.getInstance().getFileUtils().str2MD5((String) localObject1);
                    this.info.put("d_md5", localObject1.toString());
                    return;
                }
            } catch (Exception localException1) {
                this.info.put("d_md5", "unknown");
                return;
            }
            String str2 = "null";
        }
    }

    private void getGPUInfo() {
        int[] arrayOfInt = new int[2];
        try {
            this.mEGL = ((EGL10) EGLContext.getEGL());
            this.mEGLDisplay = this.mEGL.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            this.mEGL.eglInitialize(this.mEGLDisplay, arrayOfInt);
            this.mEGLConfig = chooseConfig();
            this.mEGLContext = this.mEGL.eglCreateContext(this.mEGLDisplay, this.mEGLConfig, EGL10.EGL_NO_CONTEXT, null);
            this.mEGLSurface = this.mEGL.eglCreatePbufferSurface(this.mEGLDisplay, this.mEGLConfig, new int[]{12375, 100, 12374, 100, 12344});
            this.mEGL.eglMakeCurrent(this.mEGLDisplay, this.mEGLSurface, this.mEGLSurface, this.mEGLContext);
            this.mGL = ((GL10) this.mEGLContext.getGL());
            this.info.put("GL_RENDERER", this.mGL.glGetString(7937));
            this.info.put("GL_VENDOR", this.mGL.glGetString(7936));
            this.info.put("GL_VERSION", this.mGL.glGetString(7938));
            this.info.put("GPU", this.mGL.glGetString(7937));
            return;
        } catch (Exception localException) {
            this.info.put("GL_RENDERER", "unknow");
            this.info.put("GL_VENDOR", "unknow");
            this.info.put("GL_VERSION", "unknow");
            this.info.put("GPU", "unknow");
        }
    }

    private void getTime() {
        long l = System.currentTimeMillis();
        this.info.put("timestamp", String.valueOf(String.valueOf(l)));
        String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(l));
        this.info.put("time", str);
    }

    private long getTotalMemory() {
        long l2 = 0L;
        long l1 = l2;
        try {
            BufferedReader localBufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
            l1 = l2;
            l2 = Integer.valueOf(localBufferedReader.readLine().split("\\s+")[1]).longValue() * 1024L;
            l1 = l2;
            localBufferedReader.close();
            return l2;
        } catch (IOException localIOException) {
        }
        return l1;
    }

    private boolean isRooted() {
        boolean bool1 = false;
        try {
            if (!new File("/system/bin/su").exists()) {
                boolean bool2 = new File("/system/xbin/su").exists();
                if (!bool2) {
                }
            } else {
                bool1 = true;
            }
            return bool1;
        } catch (Exception localException) {
        }
        return false;
    }

    public void addDeviceInfo(Context paramContext, String paramString1, String paramString2) {
        getDeviceInfo().put(paramString1, paramString2);
    }

    public Map<String, String> collectDeviceInfo() {
        if (this.info == null) {
            this.info = new HashMap();
            getBundleVersion();
            getDeviceMD5();
            getDeviceBasicInfo();
            getGPUInfo();
            getUdid();
        }
        Set<Entry<String, String>> entries = info.entrySet();
        Iterator<Entry<String, String>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Entry<String, String> next = iterator.next();
            String key = next.getKey();
            String value = next.getValue();
            Log.e("yb", "key = " + key + " value = " + value);
        }
        return this.info;
    }

    public List<String> getCpuInfo() {
        List<String> localArrayList = new ArrayList<>();
        try {
            BufferedReader localBufferedReader = new BufferedReader(new FileReader("/proc/cpuinfo"), 8192);
            for (; ; ) {
                String str = localBufferedReader.readLine();
                if (str == null) {
                    localBufferedReader.close();
                    return localArrayList;
                }
                localArrayList.add(str);
            }
        } catch (IOException localIOException) {
            localIOException.printStackTrace();
        }
        return localArrayList;
    }

    public Context getCtx() {
        return this.ctx;
    }

    public Map<String, String> getDeviceInfo() {
        getTime();
        getDeviceCrashInfo();
        return this.info;
    }

    public String getDeviceInfoStr(Context paramContext) {
        Map<String, String> paramContext2 = getDeviceInfo();
        StringBuilder localStringBuilder = new StringBuilder();
        Iterator localIterator = paramContext2.entrySet().iterator();
        for (; ; ) {
            if (!localIterator.hasNext()) {
                return paramContext2.toString();
            }
            Entry localEntry = (Entry) localIterator.next();
            localStringBuilder.append((String) localEntry.getKey());
            localStringBuilder.append("=");
            localStringBuilder.append((String) localEntry.getValue());
//            localStringBuilder.append(MyFileUtils.CRLF);
        }
    }

    public Map<String, String> getInfo() {
        if (this.info == null) {
            this.info = new HashMap();
        }
        return this.info;
    }

    public void getUdid() {
        String str = Settings.Secure.getString(this.ctx.getContentResolver(), Settings.Secure.ANDROID_ID);
        if (str != null)
            this.info.put("udid", str);
    }
}