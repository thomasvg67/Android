package com.example.databaseconnect;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabase extends SQLiteOpenHelper {
    public MyDatabase(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE USERS (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, SURNAME TEXT, MARKS INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS USERS");
        onCreate(db); // Recreate the table after dropping
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM USERS", null);
    }

    public Boolean insertdata(String userVal, Integer passVal, String surnameVal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", userVal);
        contentValues.put("SURNAME", surnameVal);
        contentValues.put("MARKS", passVal);
        long result = db.insert("USERS", null, contentValues);
        db.close();
        return result != -1; // Return true if insert was successful
    }

    public int deletedata(String userVal) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete("USERS", "NAME=?", new String[]{userVal});
        db.close(); // Close the database after operation
        return rowsAffected;
    }

    public Boolean updateData(String userVal, Integer passVal, String surnameVal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", userVal);
        contentValues.put("SURNAME", surnameVal);
        contentValues.put("MARKS", passVal);
        int result = db.update("USERS", contentValues, "NAME=?", new String[]{userVal});
        db.close(); // Close the database after operation
        return result > 0; // Return true if update was successful
    }
}

