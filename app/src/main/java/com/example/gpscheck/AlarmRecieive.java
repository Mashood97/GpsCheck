package com.example.gpscheck;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

public class AlarmRecieive extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.e("Service_call_", "You are in AlarmReceive class.");
            Intent background = new Intent(context, myService.class);
//        Intent background = new Intent(context, GoogleService.class);
            Log.e("AlarmReceive ", "testing called broadcast called");
            context.startForegroundService(background);
        }
        else {
            Log.e("Service_call_", "You are in AlarmReceive class.");
            Intent background = new Intent(context, myService.class);
//        Intent background = new Intent(context, GoogleService.class);
            Log.e("AlarmReceive ", "testing called broadcast called");
            context.startService(background);
        }
    }

}