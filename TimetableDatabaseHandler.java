package com.example.lotus;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class TimetableDatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "timetable.db";
    private static final String TABLE_NAME = "timetable_table";

    private static final String COLUMN_MON = "mon";
    private static final String COLUMN_TUE = "tue";
    private static final String COLUMN_WED = "wed";
    private static final String COLUMN_THU = "thu";
    private static final String COLUMN_FRI = "fri";
    private static final String COLUMN_SAT = "sat";

    private static final String DELETE_TABLE_QUERY = "drop table if exists " + TABLE_NAME;
    private static final String CREATE_TABLE_QUERY = "create table " + TABLE_NAME + " (" +
            COLUMN_MON + " TEXT," +
            COLUMN_TUE + " TEXT," +
            COLUMN_WED + " TEXT," +
            COLUMN_THU + " TEXT," +
            COLUMN_FRI + " TEXT," +
            COLUMN_SAT + " TEXT" +
            ");";


    public TimetableDatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DELETE_TABLE_QUERY);
        onCreate(sqLiteDatabase);
    }

    public boolean updateData(String[][] subs) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(DELETE_TABLE_QUERY);
        db.execSQL(CREATE_TABLE_QUERY);
        for (int i = 0; i < 8; i++) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_MON, subs[0][i]);
            values.put(COLUMN_TUE, subs[1][i]);
            values.put(COLUMN_WED, subs[2][i]);
            values.put(COLUMN_THU, subs[3][i]);
            values.put(COLUMN_FRI, subs[4][i]);
            values.put(COLUMN_SAT, subs[5][i]);
            long result = db.insert(TABLE_NAME, null, values);
            if (result == -1) return false;
        }
        return true;
    }

    public String[][] getAllData() {
        SQLiteDatabase db = getWritableDatabase();
        @SuppressLint("Recycle") Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        String[][] subs = new String[6][8];
        int idx = 0;
        while (res.moveToNext()) {
            for (int i = 0; i < 6; i++)
                subs[i][idx] = res.getString(i);
            idx++;
        }
        return subs;
    }
}
