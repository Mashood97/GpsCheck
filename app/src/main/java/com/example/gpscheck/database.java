package com.example.gpscheck;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class database extends SQLiteOpenHelper {

    private static database mInstance;
    Context contexts;

    private static final String DATABASE_NAME = "db_chekc";
    private static final int DATABASE_VERSION = 1;

    private static final String db_tbl_name = "tbl_check_gps";
    private static final String db_tbl_off_id = "tbl_off_id";
    private static final String db_tbl_title = "tbl_title";
    private static final String db_tbl_datetime = "tbl_datetime";

    public static synchronized database getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (mInstance == null) {
            mInstance = new database(context.getApplicationContext());
        }
        return mInstance;
    }

    private database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        contexts = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String create_check_gps_table = "CREATE TABLE " + db_tbl_name + " ( " + db_tbl_off_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + db_tbl_title + " TEXT NOT NULL, " + db_tbl_datetime + " TEXT NOT NULL" + ");";

        db.execSQL(create_check_gps_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + db_tbl_name);
        onCreate(db);
    }

    private String getDate(Long time) {

        Date df = new java.util.Date(time);
        String vv = new SimpleDateFormat("dd-MM-yyyy h:ma").format(df);
        return vv;
    }

    SQLiteDatabase db;
    long rows;

    public long addData(checkgps gps) {

        try {
            db = this.getWritableDatabase();
            db.beginTransaction();
            rows = 0;

            ContentValues Val = new ContentValues();
            Val.put(db_tbl_off_id, gps.getOffid());
            Val.put(db_tbl_title, gps.getTitle());
            Val.put(db_tbl_datetime, getDate(System.currentTimeMillis()));

            rows = db.insertWithOnConflict(db_tbl_name, null, Val, SQLiteDatabase.CONFLICT_REPLACE);

            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            db.close();
        }

        return rows;

    }


    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + db_tbl_name, null);
        return res;
    }
}
