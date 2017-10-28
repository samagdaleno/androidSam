package com.example.sam.myapplication.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sam on 27/10/2017.
 */

public class DBUtils extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "JuegosDB";
    public static int DATABASE_VERSION = 1;

    public static final String BOARD_TABLE = "Board";
    public static final String BOARD_BASEID = "baseId";
    public static final String BOARD_ID = "id";
    public static final String BOARD_NAME = "name";
    public static final String BOARD_AUTHOR = "author";

    public static final String SNAKE_TABLE = "Snake";
    public static final String SNAKE_BASEID = "baseId";
    public static final String SNAKE_USERID = "userId";
    public static final String SNAKE_BEGIN = "begin";
    public static final String SNAKE_DESTINATION = "destination";

    public static final String LADDER_TABLE = "Ladder";
    public static final String LADDER_BASEID = "baseId";
    public static final String LADDER_USERID = "userId";
    public static final String LADDER_BEGIN = "begin";
    public static final String LADDER_DESTINATION = "destination";

    public static final String CREATE_BOARD =
            "CREATE TABLE "+ BOARD_TABLE + "(" +
                    BOARD_BASEID + " INTEGER PRIMARY KEY, " +
                    BOARD_ID+" TEXT, " +
                    BOARD_NAME + " TEXT, " +
                    BOARD_AUTHOR + " TEXT)";

    public static final String CREATE_SNAKE =
            "CREATE TABLE "+ SNAKE_TABLE + "(" +
                    SNAKE_BASEID + " INTEGER PRIMARY KEY, " +
                    SNAKE_BEGIN+" TEXT, " +
                    SNAKE_DESTINATION + " TEXT," +
                    SNAKE_USERID + " TEXT," +
                    " FOREIGN KEY ("+SNAKE_USERID+") REFERENCES "+BOARD_TABLE+"("+BOARD_ID+"));";

    public static final String CREATE_LADDER =
            "CREATE TABLE "+ LADDER_TABLE + "(" +
                    LADDER_BASEID + " INTEGER PRIMARY KEY, " +
                    LADDER_BEGIN+" TEXT, " +
                    LADDER_DESTINATION + " TEXT," +
                    LADDER_USERID + " TEXT," +
                    " FOREIGN KEY ("+LADDER_USERID+") REFERENCES "+BOARD_TABLE+"("+BOARD_ID+"));";

    public DBUtils(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOARD);
        db.execSQL(CREATE_SNAKE);
        db.execSQL(CREATE_LADDER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BOARD_TABLE + "");
        db.execSQL("DROP TABLE IF EXISTS " + SNAKE_TABLE + "");
        db.execSQL("DROP TABLE IF EXISTS " + LADDER_TABLE + "");
        onCreate(db);
    }
}
