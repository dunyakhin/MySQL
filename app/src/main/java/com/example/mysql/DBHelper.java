package com.example.mysql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context, @Nullable String name, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="contactDb";
    public static final String TABLE_CONTACTS="contacts";
    public static final String KEY_NAME="name";
    public static final String KEY_EMAIL="email";
    public static final String KEY_ID="_id";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
sqLiteDatabase.execSQL("create table "+ TABLE_CONTACTS+ "(" + KEY_ID + " integer primary key, " +
        KEY_NAME+" text,"+KEY_EMAIL+ " text" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
sqLiteDatabase.execSQL("drop table if exists "+TABLE_CONTACTS);
onCreate(sqLiteDatabase);
    }
}
