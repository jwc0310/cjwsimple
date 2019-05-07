package com.andy.cjwsimple.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/**
 * Created by Administrator on 2018/1/24.
 */

public class AdvertisingInterface implements IInterface {
    private IBinder binder;

    public AdvertisingInterface(IBinder pBinder) {
        binder = pBinder;
    }

    public IBinder asBinder() {
        return binder;
    }

    public String getId() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        String id = "";
        try {
            data.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
            binder.transact(1, data, reply, 0);
            reply.readException();
            id = reply.readString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reply.recycle();
            data.recycle();
        }
        return id;
    }

    public String resetGaid() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        String id = "";
        try {
            data.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
            data.writeString("com.google.android.gms");
            binder.transact(1, data, reply, 0);
            reply.readException();
            id = reply.readString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reply.recycle();
            data.recycle();
        }
        return id;
    }

    public boolean isLimitAdTrackingEnabled(boolean paramBoolean)
            throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        boolean limitAdTracking = false;
        try {
            data.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
            data.writeInt(paramBoolean ? 1 : 0);
            binder.transact(2, data, reply, 0);
            reply.readException();
            limitAdTracking = 0 != reply.readInt();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reply.recycle();
            data.recycle();
        }
        return limitAdTracking;
    }
}