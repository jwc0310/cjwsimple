package com.microvirt.cjwsimple.ads;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Administrator on 2018/1/24.
 */

public class AdvertisingConnection implements ServiceConnection {

    private boolean retrieved = false;
    private final LinkedBlockingQueue<IBinder> queue = new LinkedBlockingQueue<IBinder>(
            1);

    public void onServiceConnected(ComponentName name, IBinder service) {
        try {
            this.queue.put(service);
        } catch (InterruptedException localInterruptedException) {
        }
    }

    public void onServiceDisconnected(ComponentName name) {
    }

    public IBinder getBinder() throws InterruptedException {
        if (this.retrieved)
            throw new IllegalStateException();

        this.retrieved = true;

        return (IBinder) this.queue.take();
    }
}
