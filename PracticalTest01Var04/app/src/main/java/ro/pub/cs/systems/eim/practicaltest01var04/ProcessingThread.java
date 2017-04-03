package ro.pub.cs.systems.eim.practicaltest01var04;

import android.content.Context;
import android.content.Intent;

public class ProcessingThread extends Thread {
    private Context context;
    private String messages_all;


    public ProcessingThread(Context context, String messages_all) {
        this.context = context;
        this.messages_all = messages_all;
    }

    @Override
    public void run() {
        for (String message : messages_all.split(", ")) {
            sendMessage(message);
            sleep();
        }

//        Log.d(Constants.TAG, "Thread.run() was invoked, PID: " + android.os.Process.myPid() + " TID: " + android.os.Process.myTid());

    }

    private void sleep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    private void sendMessage(String message) {
        Intent intent = new Intent();
        intent.setAction("difuzare");
        intent.putExtra("difuzare.string", message);
        context.sendBroadcast(intent);


    }
}
