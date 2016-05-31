package com.repsly.careline.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.repsly.careline.model.User;

/**
 * Created by tosulc on 31.05.2016..
 */
public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context) {
        super(context, "carelineDatabase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "Create table User(accountRowId TEXT PRIMARY KEY, name Text, address TEXT, manager TEXT)");
        db.execSQL("Create table Schedule(id TEXT PRIMARY KEY, dateTime TEXT, note TEXT)");
        db.execSQL(
                "Create table ScheduleItem(id TEXT PRIMARY KEY, scheduleId TEXT, medicineId TEXT, quantity TEXT)");
        db.execSQL("Create table Medicine(id TEXT PRIMARY KEY, name TEXT, type TEXT)");

        //TODO insert some dummy data
        db.execSQL("Insert into User values('fasdfasd','Ime babe', 'Adresa babe', '0')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS User");
        db.execSQL("DROP TABLE IF EXISTS Schedule");
        db.execSQL("DROP TABLE IF EXISTS ScheduleItem");
        db.execSQL("DROP TABLE IF EXISTS Medicine");
        onCreate(db);
    }

    public void saveUser(String accountRowId, String name, String address, boolean manager) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("accountRowId", accountRowId);
        cv.put("name", name);
        cv.put("address", address);
        cv.put("manager", (manager) ? 1 : 0);
        db.insert("User", null, cv);
        db.close();
    }

    public User getUser(String id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c;
        boolean hadRecods;
        User user = new User();
        c = db
                .query("User", null, "accountRowId=?", new String[]{id},
                       null, null, null, null);
        if (c != null) {
            hadRecods = c.moveToFirst();
            if (hadRecods) {
                user.setId(c.getString(c.getColumnIndex("accountRowId")));
                user.setAddress(c.getString(c.getColumnIndex("address")));
                user.setName(c.getString(c.getColumnIndex("name")));
                user.setManager(c.getString(c.getColumnIndex("manager")));
            }
            c.close();
        }
        return user;
    }

}
