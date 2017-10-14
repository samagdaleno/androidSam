package com.example.sam.apiclient3000;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.sam.apiclient3000.Models.Comments;
import com.example.sam.apiclient3000.Models.Posts;
import com.example.sam.apiclient3000.Utils.CommentHelper;
import com.example.sam.apiclient3000.Utils.PostsHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final String postUrl = "https://jsonplaceholder.typicode.com/posts";
    final String commentsUrl = "http://jsonplaceholder.typicode.com/comments";
    ArrayList<Posts> posts;
    ArrayList<Comments> comments;
    CommentHelper ch;
    PostsHelper ph;
    TextView txtString;
    TextView txtString2;
    Button btnTest;
    Button btnSyncPosts;
    Button btnSyncComments;
    Button btnViewPosts;
    Button btnViewComments;
    Button btnClear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ch = new CommentHelper(this);
        ph = new PostsHelper(this);
        txtString = (TextView) findViewById(R.id.textString);
        txtString2 = (TextView) findViewById(R.id.textString2);
        btnTest = (Button) findViewById((R.id.button1));
        btnSyncPosts = (Button) findViewById((R.id.buttonSyncP));
        btnSyncComments = (Button) findViewById((R.id.buttonSyncC));
        btnViewPosts = (Button) findViewById((R.id.buttonPosts));
        btnViewComments = (Button) findViewById((R.id.buttonComments));
        btnClear = (Button) findViewById((R.id.buttonClear));


        final JsonArrayRequest postsArrayRequest = new JsonArrayRequest(Request.Method.GET, postUrl,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String result = "";
                posts = new ArrayList<Posts>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        //result += response.getString(i) + "\n\n";
                        posts.add(createPost(response.getString(i)));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                txtString.setText(error.toString());
            }
        });

        final JsonArrayRequest commentsArrayRequest = new JsonArrayRequest(Request.Method.GET, commentsUrl,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String result = "";
                comments = new ArrayList<Comments>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        //result += response.getString(i) + "\n\n";
                        comments.add(createComment(response.getString(i)));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                txtString.setText(error.toString());
            }
        });


    }

    public Posts createPost(String response) {
        Posts post = new Posts();
        try {
            JSONObject jsonObject = new JSONObject(response);
            String userId = jsonObject.getString("userId");
            String id = jsonObject.getString("id");
            String title = jsonObject.getString("title");
            String body = jsonObject.getString("body");
            ph.open();
            post = ph.addPost(id, userId, title, body);
            ph.close();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return post;
    }

    public Comments createComment(String response) {
        Comments comment = new Comments();
        try {
            JSONObject jsonObject = new JSONObject(response);
            String postId = jsonObject.getString("postId");
            String id = jsonObject.getString("id");
            String name = jsonObject.getString("name");
            String email = jsonObject.getString("email");
            String body = jsonObject.getString("body");
            ch.open();
            comment = ch.addComment(id, postId, name, email, body);
            ch.close();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return comment;
    }
}


