package ro.pub.cs.systems.eim.practicaltest01var04;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class PracticalTest01Var04Service extends Service {
    public static final String TAG = "SERIVCE";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate() method was invoked");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind() method was invoked");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind() method was invoked");
        return true;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(TAG, "onRebind() method was invoked");
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy() method was invoked");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() method was invoked");
        // TODO: exercise 5 - implement and start the ProcessingThread

        String message_all = intent.getExtras().getString("mesaj_catre_service");

        ProcessingThread myThread = new ProcessingThread(getApplicationContext(), message_all);
        myThread.run();
        return START_REDELIVER_INTENT;
    }
}