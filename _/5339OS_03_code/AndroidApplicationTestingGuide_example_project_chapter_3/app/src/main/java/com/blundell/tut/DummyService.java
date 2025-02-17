package com.blundell.tut;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class DummyService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return new LocalBinder();
    }

    public class LocalBinder extends Binder {
        DummyService getService() {
            return DummyService.this;
        }
    }

}
