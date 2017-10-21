package com.example.sam.apiclient3000.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sam on 22/09/2017.
 */

public class DBUtils extends SQLiteOpenHelper{
    public static final String DATABASE_NAME="API3000DB2";
    public static final int DATABASE_VERSION = 2;

    public static final String COMMENTS_TABLE_NAME="COMMENTS";
    public static final String COMMENTS_BASEID = "baseid";
    public static final String COMMENTS_ID = "id";
    public static final String COMMENTS_POSTID = "postId";
    public static final String COMMENTS_NAME = "name";
    public static final String COMMENTS_EMAIL = "email";
    public static final String COMMENTS_BODY = "body";

    public static final String POSTS_TABLE_NAME="POSTS";
    public static final String POSTS_BASEID = "baseId";
    public static final String POSTS_ID = "id";
    public static final String POSTS_USERID = "userId";
    public static final String POSTS_TITLE = "title";
    public static final String POSTS_BODY = "body";


    public static final String DATABASE_CREATE_POSTS="CREATE TABLE "+POSTS_TABLE_NAME+" ("+
            POSTS_BASEID + " INTEGER PRIMARY KEY," +
            POSTS_ID + " TEXT, " +
            POSTS_USERID+ " TEXT, " +
            POSTS_TITLE+ " TEXT, " +
            POSTS_BODY+ " TEXT)";

    public static final String DATABASE_CREATE_COMMENTS="CREATE TABLE "+COMMENTS_TABLE_NAME+" ("+
            COMMENTS_BASEID + " INTEGER PRIMARY KEY," +
            COMMENTS_ID + " text," +
            COMMENTS_POSTID+ " text," +
            COMMENTS_NAME+ " text," +
            COMMENTS_EMAIL+ " TEXT, "+
            COMMENTS_BODY+ " text"+
            ")";

    public DBUtils(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_POSTS);
        db.execSQL(DATABASE_CREATE_COMMENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + COMMENTS_TABLE_NAME + "");
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_CREATE_COMMENTS + "");
        onCreate(db);
    }
}