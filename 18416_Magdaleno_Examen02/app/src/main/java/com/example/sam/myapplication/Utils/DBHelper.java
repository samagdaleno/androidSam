package com.example.sam.myapplication.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.sam.myapplication.Models.Board;
import com.example.sam.myapplication.Models.Ladder;
import com.example.sam.myapplication.Models.Snake;

import java.util.ArrayList;

/**
 * Created by Sam on 27/10/2017.
 */

public class DBHelper {
    private DBUtils dbHelper;
    private SQLiteDatabase database;

    private String[] BOARD_TABLE_COLUMNS =
            {
                    DBUtils.BOARD_BASEID,
                    DBUtils.BOARD_ID,
                    DBUtils.BOARD_NAME,
                    DBUtils.BOARD_AUTHOR
            };

    private String[] SNAKE_TABLE_COLUMNS =
            {
                    DBUtils.SNAKE_BASEID,
                    DBUtils.SNAKE_BEGIN,
                    DBUtils.SNAKE_DESTINATION,
                    DBUtils.SNAKE_USERID
            };

    private String[] LADDER_TABLE_COLUMNS =
            {
                    DBUtils.LADDER_BASEID,
                    DBUtils.LADDER_BEGIN,
                    DBUtils.LADDER_DESTINATION,
                    DBUtils.LADDER_USERID
            };
    public DBHelper(Context context) {
        dbHelper = new DBUtils(context);
    }
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close() {
        database.close();
    }


    public Board addBoard(String id, String name, String author) {
        ContentValues values = new ContentValues();
        values.put(DBUtils.BOARD_ID, id);
        values.put(DBUtils.BOARD_NAME, name);
        values.put(DBUtils.BOARD_AUTHOR, author);;
        long boardId = database.insert(DBUtils.BOARD_TABLE, null, values);
        Cursor cursor = database.query(DBUtils.BOARD_TABLE, BOARD_TABLE_COLUMNS,
                DBUtils.BOARD_BASEID + " = " + boardId, null, null, null, null);
        cursor.moveToFirst();
        Board board = parseBoard(cursor);
        cursor.close();
        return board;
    }

    public Snake addSnake(String begin, String destination, String userId) {
        ContentValues values = new ContentValues();
        values.put(DBUtils.SNAKE_BEGIN, begin);
        values.put(DBUtils.SNAKE_DESTINATION, destination);
        values.put(DBUtils.SNAKE_USERID, userId);;
        long snakeId = database.insert(DBUtils.SNAKE_TABLE, null, values);
        Cursor cursor = database.query(DBUtils.SNAKE_TABLE, SNAKE_TABLE_COLUMNS,
                DBUtils.BOARD_BASEID + " = " + snakeId, null, null, null, null);
        cursor.moveToFirst();
        Snake snake = parseSnake(cursor);
        cursor.close();
        return snake;
    }

    public Ladder addLadder(String begin, String destination, String userId) {
        ContentValues values = new ContentValues();
        values.put(DBUtils.LADDER_BEGIN, begin);
        values.put(DBUtils.LADDER_DESTINATION, destination);
        values.put(DBUtils.LADDER_USERID, userId);;
        long ladderId = database.insert(DBUtils.LADDER_USERID, null, values);
        Cursor cursor = database.query(DBUtils.LADDER_TABLE, LADDER_TABLE_COLUMNS,
                DBUtils.LADDER_BASEID + " = " + ladderId, null, null, null, null);
        cursor.moveToFirst();
        Ladder ladder = parseLadder(cursor);
        cursor.close();
        return ladder;
    }


    private Board parseBoard(Cursor cursor) {
        Board board = new Board();
        board.setId(cursor.getString(cursor.getColumnIndex(DBUtils.BOARD_ID)));
        board.setName(cursor.getString(cursor.getColumnIndex(DBUtils.BOARD_NAME)));
        board.setAuthor(cursor.getString(cursor.getColumnIndex(DBUtils.BOARD_AUTHOR)));
        return board;
    }

    private Snake parseSnake(Cursor cursor) {
        Snake snake = new Snake();
        snake.setBegin(cursor.getString(cursor.getColumnIndex(DBUtils.SNAKE_BEGIN)));
        snake.setBoard_id(cursor.getString(cursor.getColumnIndex(DBUtils.SNAKE_USERID)));
        snake.setDestination(cursor.getString(cursor.getColumnIndex(DBUtils.SNAKE_DESTINATION)));
        return snake;
    }

    public ArrayList<Board> getAllBoards() {
        ArrayList<Board> boards = new ArrayList<Board>();
        Cursor cursor = database.query(DBUtils.BOARD_TABLE, BOARD_TABLE_COLUMNS, null, null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            boards.add(parseBoard(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return boards;
    }

    private Ladder parseLadder(Cursor cursor) {
        Ladder ladder = new Ladder();
        ladder.setBegin(cursor.getString(cursor.getColumnIndex(DBUtils.LADDER_BEGIN)));
        ladder.setBoard_id(cursor.getString(cursor.getColumnIndex(DBUtils.LADDER_USERID)));
        ladder.setDestination(cursor.getString(cursor.getColumnIndex(DBUtils.LADDER_DESTINATION)));
        return ladder;
    }

}
