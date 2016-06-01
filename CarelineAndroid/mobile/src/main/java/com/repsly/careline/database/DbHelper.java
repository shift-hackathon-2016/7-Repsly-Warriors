package com.repsly.careline.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.repsly.careline.model.CareReceiver;
import com.repsly.careline.model.Medicine;
import com.repsly.careline.model.ReminderScheduleItem;
import com.repsly.careline.model.Schedule;
import com.repsly.careline.model.ScheduleItem;
import com.repsly.careline.model.User;
import com.repsly.careline.model.network.UserData;

import java.util.ArrayList;
import java.util.List;

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
                "Create table User(accountRowId TEXT PRIMARY KEY, name Text, address TEXT, manager TEXT, avatar TEXT, email TEXT)");
        db.execSQL("Create table Schedule(id TEXT PRIMARY KEY, dateTime TEXT, note TEXT)");
        db.execSQL(
                "Create table ScheduleItem(id TEXT PRIMARY KEY, scheduleId TEXT, medicineRowId TEXT, medicineId TEXT, name TEXT, quantity TEXT)");
        db.execSQL("Create table Medicine(id TEXT PRIMARY KEY, name TEXT, medImg TEXT, description TEXT, medColor TEXT, medType TEXT, quantity TEXT)");
        db.execSQL(
                "Create table Carereicivers(id TEXT PRIMARY KEY, name Text, address TEXT, avatar TEXT, lastLocation TEXT)");

        //TODO insert some dummy data
        /*db.execSQL("Insert into User values('fasdfasd','Ime babe', 'Adresa babe', '0')");
        db.execSQL("Insert into User values('fasdfasd','Ime babe', 'Velebitska 78, Split, Croatia', '0')"); */
        db.execSQL("Insert into Schedule values('1','2016-05-31T15:00:00','ovo je note')");
        db.execSQL("Insert into ScheduleItem values('1','1','dfgsdfgsf','1','haha','2')");
        db.execSQL("Insert into ScheduleItem values('2','1','xcyvcxy','2','tralala','1')");
        db.execSQL("Insert into Medicine values('1','Benadryl','','pill', '#EE5D8E','pill', '2')");
        db.execSQL("Insert into Medicine values('2','Adderall','','pill', '#EFA867','pill', '5')");
        db.execSQL("Insert into Medicine values('3','Meloxicam','','pill', '#8BD73E','pill', '7')");
        db.execSQL("Insert into Medicine values('4','Tramadol','','pill', '#9B9FDA','pill', '12')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS User");
        db.execSQL("DROP TABLE IF EXISTS Schedule");
        db.execSQL("DROP TABLE IF EXISTS ScheduleItem");
        db.execSQL("DROP TABLE IF EXISTS Medicine");
        onCreate(db);
    }


    public void saveCareReceiver(CareReceiver userData) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id", userData.userId);
        cv.put("name", userData.name);
        cv.put("address", userData.address);
        cv.put("avatar", userData.avatar);
        cv.put("lastLocation", userData.lastMovement);
        db.insert("Carereicivers", null, cv);
        db.close();
    }

    public List<CareReceiver> getCareReceivers() {
        List<CareReceiver> items=new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c;
        boolean hadRecods;
        User user = new User();
        c = db
                .query("Carereicivers", null, null, null,
                       null, null, null, null);
        if (c != null) {
            while(hadRecods = c.moveToFirst()) {
                if (hadRecods) {
                    CareReceiver careReceiver=new CareReceiver();
                    careReceiver.setId(c.getString(c.getColumnIndex("accountRowId")));
                    careReceiver.setAddress(c.getString(c.getColumnIndex("address")));
                    careReceiver.setName(c.getString(c.getColumnIndex("name")));
                    careReceiver.setLastMovement(c.getString(c.getColumnIndex("lastLocation")));
                    items.add(careReceiver);
                }
            }
            c.close();
        }
        return items;
    }

    public void saveUser(UserData userData) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("accountRowId", userData.accountRowId);
        cv.put("name", userData.name);
        cv.put("address", userData.address);
        cv.put("manager", (userData.manager) ? 1 : 0);
        cv.put("email", userData.email);
        cv.put("avatar", userData.avatar);
        db.insert("User", null, cv);
        db.close();
    }


    public User getUser() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c;
        boolean hadRecods;
        User user = new User();
        c = db
                .query("User", null, null, null,
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

    public void saveSchedules(List<Schedule> schedules) {
        SQLiteDatabase db = getWritableDatabase();
        for (Schedule s : schedules) {
            //Schedule(id TEXT PRIMARY KEY, dateTime TEXT, note TEXT)");
            ContentValues cv = new ContentValues();
            cv.put("id", s.id);
            cv.put("dateTime", s.dateTime);
            cv.put("note", s.note);
            db.insert("Schedule", null, cv);
            for (ScheduleItem si : s.scheduleItems) {
                //ScheduleItem(id TEXT PRIMARY KEY, scheduleId TEXT, medicineId TEXT, quantity TEXT)
                ContentValues cv2 = new ContentValues();
                cv2.put("id", si.id);
                cv2.put("medicineId", si.medicineId);
                cv2.put("medicineRowId", si.medicineRowId);
                cv2.put("scheduleId", s.id); //schedule id!
                cv2.put("name", si.name);
                cv2.put("quantity", si.quantity);
                db.insert("ScheduleItem", null, cv2);
            }
        }
        db.close();
    }

    public void saveMedicines(List<Medicine> medicines) {
        SQLiteDatabase db = getWritableDatabase();
        for (Medicine m : medicines) {
            //Medicine(id TEXT PRIMARY KEY, name TEXT, MedImg TEXT, Description TEXT, MedColor TEXT,
            //MedType TEXT, Quantity TEXT)");
            ContentValues cv = new ContentValues();
            cv.put("id", m.id);
            cv.put("name", m.name);
            cv.put("medImg", m.medImg);
            cv.put("description", m.description);
            cv.put("medColor", m.medColor);
            cv.put("medType", m.medType);
            cv.put("quantity", m.quantity);
            db.insert("Medicine", null, cv);
        }
        db.close();
    }

    public ArrayList<ReminderScheduleItem> getScheduleForToday() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c;
        boolean hadRecods;
        ArrayList<ReminderScheduleItem> scheduleItems = new ArrayList<>();
        c = db.rawQuery(
                "select s.datetime, si.id, m.name, m.medType, si.quantity, m.medColor from Schedule as s left join ScheduleItem as si on s.id = si.scheduleId\n" +
                        "left join Medicine as m on si.medicineId = m.id", null);
        if (c != null) {
            hadRecods = c.moveToFirst();
            while (hadRecods) {
                ReminderScheduleItem rsi = new ReminderScheduleItem();
                rsi.name = c.getString(c.getColumnIndex("name"));
                rsi.dateTime = c.getString(c.getColumnIndex("dateTime"));
                rsi.scheduleItemRowId = c.getString(c.getColumnIndex("id"));
                //rsi.note = c.getString(c.getColumnIndex("note"));
                rsi.quantity = c.getString(c.getColumnIndex("quantity"));
                rsi.medColor = c.getString(c.getColumnIndex("medColor"));
                rsi.type = c.getString(c.getColumnIndex("medType"));
                rsi.medColor = c.getString(c.getColumnIndex("medColor"));
                scheduleItems.add(rsi);
                hadRecods = c.moveToNext();
            }
            c.close();
        }
        return scheduleItems;
    }

}
