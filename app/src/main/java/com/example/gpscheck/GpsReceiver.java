package com.example.gpscheck;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Build;

public class GpsReceiver extends BroadcastReceiver {

    public interface LocationCallBack {
        void turnedOn();

        void turnedOff();
    }

    private final LocationCallBack locationCallBack;

    public GpsReceiver(LocationCallBack iLocationCallBack) {
        this.locationCallBack = iLocationCallBack;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                locationCallBack.turnedOn();

            } else {
                locationCallBack.turnedOff();

            }

        } else {
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                locationCallBack.turnedOn();

            } else {
                locationCallBack.turnedOff();

            }

        }
    }
}