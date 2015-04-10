package com.example.prince.mytoolkit;

/**
 * Created by Prince on 7/4/2015.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class TodoListSQLHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "TodoList.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE = "tasks";
    public static final String TASK = "task";
    public static final String _ID = BaseColumns._ID;

    public TodoListSQLHelper(Context context) {
        //1 is todo list database version
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqlDB) {
        String createTodoListTable = String.format("CREATE TABLE %s (" +
                        "_id INTEGER PRIMARY KEY AUTOINCREMENT, " + "%s TEXT)", TABLE, TASK);
        sqlDB.execSQL(createTodoListTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqlDB, int i, int i2) {
        sqlDB.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(sqlDB);
    }
}