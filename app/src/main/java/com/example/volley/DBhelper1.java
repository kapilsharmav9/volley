package com.example.volley;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.FileInputStream;

public class DBhelper1 extends SQLiteOpenHelper {
    static final String DBNAME = "Android2020";
    static final String TABLE_1 = "UploadImages";
//    static final String COL_1 = " ID INTEGER PRIMARY KEY   AUTOINCREMENT";
//    static final String COL_2 = "Image";
//

    public DBhelper1(@Nullable Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("create table '" + TABLE_1 + "'('"+COL_1+"' varchar(100),'"+COL_2+"' varchar(100))");
        db.execSQL("create table uploads(id integer primary key ,images blob not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }


    public boolean InsertEMP(int id, String pic) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            FileInputStream fis = new FileInputStream(pic);
            byte[] imgbyte = new byte[fis.available()];
            fis.read(imgbyte);
            ContentValues cv = new ContentValues();
            cv.put("id", id);
            cv.put("img", pic);
            long l = db.insert(TABLE_1, null, cv);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }

    public Cursor getData(int id, String img) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cs = db.rawQuery("SELECT * FROM " + TABLE_1 + " WHERE id='" + id + "' AND images='" + img + "'", null);
        return cs;
    }


}