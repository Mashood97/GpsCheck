package com.example.gpscheck;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    database db;
    Button btn;

    AlarmManager alarmManager;

    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = database.getInstance(MainActivity.this);

        btn = findViewById(R.id.buttongetdata);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getGpsData();
            }
        });



        if (alarmManager == null) {
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(this, AlarmRecieive.class);
            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 30000,
                    pendingIntent);
        }




//        registerReceiver(new GpsReceiver(new GpsReceiver.LocationCallBack() {
//            @Override
//            public void turnedOn() {
//                System.out.println("GpsReceiver is turned on" + getDate(System.currentTimeMillis()));
//                checkGpsData("Gps is on");
//            }
//
//            @Override
//            public void turnedOff() {
//                System.out.println("GpsReceiver is turned off" + getDate(System.currentTimeMillis()));
//                checkGpsData("Gps is off");
//            }
//        }), new IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION));


    }

    private void checkGpsData(String titlle) {
        checkgps gps = new checkgps();
        gps.setTitle(titlle);
        long id = db.addData(gps);
        gps.setOffid(String.valueOf(id));
        if (id <= 0) {
            Toast.makeText(MainActivity.this, "Cant insert", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "inserted", Toast.LENGTH_SHORT).show();
        }
    }

    private void getGpsData() {
        Cursor res = db.getData();
        if (res.getCount() == 0) {
            Toast.makeText(MainActivity.this, "Cant get", Toast.LENGTH_SHORT).show();
        }
        while (res.moveToNext()) {
            String off_id = res.getString(res.getColumnIndex("tbl_off_id"));
            String title = res.getString(res.getColumnIndex("tbl_title"));
            String datetime = res.getString(res.getColumnIndex("tbl_datetime"));
            Toast.makeText(this, "offline id " + off_id + "\n" + "title" + title + "\n" + "datetime" + datetime + "\n", Toast.LENGTH_SHORT).show();
            System.out.println("offline id " + off_id + "\n" + "title" + title + "\n" + "datetime" + datetime + "\n");

        }
    }

    private String getDate(Long time) {

        Date df = new java.util.Date(time);
        String vv = new SimpleDateFormat("dd-MM-yyyy h:ma").format(df);
        return vv;
    }
}







