package com.example.tpnotes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "NotesDB";
    public static final int SCHEMA = 1;
    public static final String TABLE_NOTES = "Notes";
    public static final String NOTES_ID = "id_";
    public static final String NOTES_TITLE = "title";
    public static final String NOTES_BODY = "body";

    public DbHelper(@Nullable Context context){
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NOTES +
                " (" + NOTES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NOTES_TITLE + " TEXT, " +
                NOTES_BODY + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        onCreate(db);
    }
}
