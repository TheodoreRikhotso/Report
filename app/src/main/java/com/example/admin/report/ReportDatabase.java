package com.example.admin.report;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Admin on 8/16/2017.
 */

public class ReportDatabase extends SQLiteOpenHelper {
    private static final String TABLE_REPORT="table_report";
    private static final String KEY_RPORTID ="id";
    private static final String KEY_SUBJECT="subject";
    private static final String KEY_MARKS ="marks";
    private static final String KEY_TERM ="term";
    private static final String KEY_COMMENT ="comment";


    public ReportDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
