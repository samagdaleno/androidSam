package com.example.sam.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.sam.myapplication.Models.Board;
import com.example.sam.myapplication.Utils.DBHelper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class MainActivity extends AppCompatActivity {
    Button btnTest, btnSyncBoards;
    TextView testView;
    DBHelper db;
    ArrayList<Board> boards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHelper(this);
        btnTest = (Button) findViewById((R.id.btnTest));
        testView =(TextView)findViewById(R.id.testView);
        btnSyncBoards = (Button) findViewById((R.id.btnSync));
        String boardsUrl = "https://api.myjson.com/bins/19mgzn";
        final RequestQueue queue = Volley.newRequestQueue(this);

        final JsonArrayRequest postsArrayRequest = new JsonArrayRequest(Request.Method.GET, boardsUrl,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String result = "";
                boards = new ArrayList<Board>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        result += response.getString(i) + "\n\n";
                        boards.add(createBoard(response.getString(i)));
                        testView.setText(result);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                testView.setText(error.toString());
            }
        });



        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int[] board = new int[100];

                for (int i = 0; i < 99; i++)
                    board[i] = -1;

                // Ladders
                board[32] = 62;
                board[42] = 68;
                board[12] = 98;

                // Snakes
                board[95] = 13;
                board[97] = 25;


                testView.setText( "Minimum of dice throws are: " + MinimumDiceThrows(board) + "\n"
                        +"Ladders are in: \n" +
                        "board[32] = 62;\n" +
                        "board[42] = 68;\n" +
                        "board[12] = 98;");
            }
        });

        btnSyncBoards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                queue.add(postsArrayRequest);
            }
        });
    }

    public Board createBoard(String response) {
        Board board = new Board();
        try {
            JSONObject jsonObject = new JSONObject(response);
            String id = jsonObject.getString("id");
            String name = jsonObject.getString("name");
            String author = jsonObject.getString("author");
            db.open();
            board = db.addBoard(id, name, author);
            db.close();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return board;
    }



    static int MinimumDiceThrows(int board[]) {
        int cells = 100;
        int[] diceThrows = new int[6];
        boolean[] visited = new boolean[cells];

        for (int i = 0; i < cells; i++) {
            visited[i] = false;
        }

        for (int i = 1; i < 6; i++) {
            diceThrows[i] = i;
        }


        Queue q = new LinkedList<Queue>();

        visited[0] = true;

        while (!q.isEmpty()) {

            q.remove();
        }

        return 3;
    }
}
