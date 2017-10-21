package com.example.sam.apiclient3000.Utils;

/**
 * Created by Sam on 06/10/2017.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.sam.apiclient3000.Models.Comments;

import java.util.ArrayList;


public class CommentHelper {
    private DBUtils dbHelper;
    private SQLiteDatabase database;
    private String[] COMMENTS_TABLE_COLUMNS = {
            DBUtils.COMMENTS_BASEID,
            DBUtils.COMMENTS_ID,
            DBUtils.COMMENTS_POSTID,
            DBUtils.COMMENTS_NAME,
            DBUtils.COMMENTS_EMAIL,
            DBUtils.COMMENTS_BODY
    };

    public CommentHelper(Context context){
        dbHelper = new DBUtils(context);
    }

    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public Comments addComment(String id, String postid, String name, String email, String body){
        ContentValues values = new ContentValues();
        values.put(DBUtils.COMMENTS_ID, id);
        values.put(DBUtils.COMMENTS_POSTID, postid);
        values.put(DBUtils.COMMENTS_NAME, name);
        values.put(DBUtils.COMMENTS_EMAIL, email);
        values.put(DBUtils.COMMENTS_BODY, body);

        long lCustomerID = database.insert(DBUtils.COMMENTS_TABLE_NAME, null, values);

        Cursor cursor = database.query(DBUtils.COMMENTS_TABLE_NAME,
                COMMENTS_TABLE_COLUMNS,
                DBUtils.COMMENTS_BASEID +" = " + lCustomerID, null, null, null, null);
        cursor.moveToFirst();
        Comments oComments = parseComments(cursor);
        cursor.close();
        return oComments;
    }

    public int deleteComment(int nCommentID){
        return database.delete(DBUtils.COMMENTS_TABLE_NAME, DBUtils.COMMENTS_ID+ " = " + nCommentID, null);
    }

    public void deleteAllComments() {
        database.delete(DBUtils.COMMENTS_TABLE_NAME, DBUtils.COMMENTS_ID + " > 0", null);
    }

    public ArrayList<Comments> getAllComments(){
        ArrayList<Comments>oLComments = new ArrayList<Comments>();
        Cursor cursor = database.query(DBUtils.COMMENTS_TABLE_NAME, COMMENTS_TABLE_COLUMNS, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            oLComments.add(parseComments(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return oLComments;
    }

    public ArrayList<Comments> getAllCommentsByPostId(String postId){
        ArrayList<Comments> oLComments = new ArrayList<Comments>();
        Cursor cursor = database.query(DBUtils.COMMENTS_TABLE_NAME, COMMENTS_TABLE_COLUMNS, DBUtils.COMMENTS_POSTID +" = "+ postId, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            oLComments.add(parseComments(cursor));
            cursor.moveToNext();
        }
        cursor.close();

        return oLComments;
    }

    private Comments parseComments(Cursor cursor){
        Comments oComment = new Comments();
        oComment.setId(cursor.getString(cursor.getColumnIndex(DBUtils.COMMENTS_ID)) + "");
        oComment.setName(cursor.getString(cursor.getColumnIndex(DBUtils.COMMENTS_NAME)) + "");
        oComment.setBody(cursor.getString(cursor.getColumnIndex(DBUtils.COMMENTS_BODY)));
        oComment.setEmail(cursor.getString(cursor.getColumnIndex(DBUtils.COMMENTS_EMAIL)));
        oComment.setPostid(cursor.getString(cursor.getColumnIndex(DBUtils.COMMENTS_POSTID)));

        return oComment;
    }
}
