package com.example.sam.apiclient3000.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.sam.apiclient3000.Models.Posts;

import java.util.ArrayList;

/**
 * Created by Sam on 06/10/2017.
 */

public class PostsHelper {
    private DBUtils dbHelper;
    private SQLiteDatabase database;
    private String[] POSTS_TABLE_COLUMNS = {
            DBUtils.POSTS_TITLE,
            DBUtils.POSTS_USERID,
            DBUtils.POSTS_BODY,
            DBUtils.POSTS_ID
    };

    public PostsHelper(Context context){
        dbHelper = new DBUtils(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public Posts addPost(String id, String title, String userid, String body){
        ContentValues values = new ContentValues();
        values.put(DBUtils.POSTS_ID, id);
        values.put(DBUtils.POSTS_BODY, body);
        values.put(DBUtils.POSTS_USERID, userid);
        values.put(DBUtils.POSTS_TITLE, title);

        long lCustomerID = database.insert(DBUtils.POSTS_TABLE_NAME, null, values);

        Cursor cursor = database.query(DBUtils.POSTS_TABLE_NAME,
                POSTS_TABLE_COLUMNS,
                DBUtils.POSTS_ID+" = " + lCustomerID, null, null, null, null);
        cursor.moveToFirst();
        Posts oPosts = parsePosts(cursor);
        cursor.close();
        return oPosts;
    }

    public int deletePost(int nPostID){
        return database.delete(DBUtils.POSTS_TABLE_NAME, DBUtils.POSTS_ID+ " = " + nPostID, null);
    }

    public ArrayList<Posts> getAllPosts(){
        ArrayList<Posts>oLPosts = new ArrayList<Posts>();
        Cursor cursor = database.query(DBUtils.POSTS_TABLE_NAME, POSTS_TABLE_COLUMNS, null, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            oLPosts.add(parsePosts(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return oLPosts;
    }

    private Posts parsePosts(Cursor cursor){
        Posts oPost = new Posts();
        oPost.setId(cursor.getString(cursor.getColumnIndex(DBUtils.POSTS_ID)));
        oPost.setBody(cursor.getString(cursor.getColumnIndex(DBUtils.POSTS_BODY)));
        oPost.setTitle(cursor.getString(cursor.getColumnIndex(DBUtils.POSTS_TITLE)));
        oPost.setUserId(cursor.getString(cursor.getColumnIndex(DBUtils.POSTS_USERID)));

        return oPost;
    }
}
