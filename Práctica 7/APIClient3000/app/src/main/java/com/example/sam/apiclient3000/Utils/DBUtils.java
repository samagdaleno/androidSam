package com.example.sam.apiclient3000.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sam on 22/09/2017.
 */

public class DBUtils extends SQLiteOpenHelper{
    public static final String DATABASE_NAME="API3000.db";
    public static final int DATABASE_VERSION = 2;

    public static final String COMMENTS_TABLE_NAME="COMMENTS";
    public static final String COMMENTS_BASEID = "_baseid";
    public static final String COMMENTS_ID = "_id";
    public static final String COMMENTS_POSTID = "_postid";
    public static final String COMMENTS_NAME = "_name";
    public static final String COMMENTS_EMAIL = "_email";
    public static final String COMMENTS_BODY = "_body";

    public static final String POSTS_TABLE_NAME="POSTS";
    public static final String POSTS_BASEID = "_baseid";
    public static final String POSTS_ID = "_id";
    public static final String POSTS_USERID = "_userid";
    public static final String POSTS_TITLE = "_title";
    public static final String POSTS_BODY = "_body";


    public static final String DATABASE_CREATE_POSTS="CREATE TABLE "+COMMENTS_TABLE_NAME+" ("+
            COMMENTS_BASEID + " INTEGER PRIMARY KEY," +
            COMMENTS_ID + " TEXT," +
            COMMENTS_POSTID+ " TEXT," +
            COMMENTS_NAME+ " TEXT," +
            COMMENTS_EMAIL+ " TEXT"+
            COMMENTS_BODY+ " TEXT"+
            ")";

    public static final String DATABASE_CREATE_COMMENTS="CREATE TABLE "+POSTS_TABLE_NAME+" ("+
            POSTS_BASEID + " INTEGER PRIMARY KEY," +
            POSTS_ID + " text," +
            POSTS_USERID+ " text," +
            POSTS_TITLE+ " text," +
            POSTS_BODY+ " text"+
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