package com.example.sam.apiclient3000;

import android.content.Intent;
import android.support.design.widget.Snackbar;
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
import com.example.sam.apiclient3000.Models.Comments;
import com.example.sam.apiclient3000.Models.Posts;
import com.example.sam.apiclient3000.Utils.CommentHelper;
import com.example.sam.apiclient3000.Utils.PostsHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView txtString, txtString2, txtId;
    Button btnTest, btnSyncPosts, btnSyncComments, btnViewPosts, btnViewComments, btnClear, btnList;
    ArrayList<Posts> posts;
    ArrayList<Comments> comments;
    String postUrl, commentUrl;
    CommentHelper ch;
    PostsHelper ph;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ch = new CommentHelper(this);
        ph = new PostsHelper(this);
        txtString = (TextView) findViewById(R.id.textString);
        txtString2 = (TextView) findViewById(R.id.textString2);
        txtId = (TextView) findViewById(R.id.textId);
        btnTest = (Button) findViewById((R.id.button1));
        btnSyncPosts = (Button) findViewById((R.id.buttonSyncP));
        btnSyncComments = (Button) findViewById((R.id.buttonSyncC));
        btnViewPosts = (Button) findViewById((R.id.buttonPosts));
        btnViewComments = (Button) findViewById((R.id.buttonComments));
        btnClear = (Button) findViewById((R.id.buttonClear));
        btnList = (Button) findViewById((R.id.listBtn));
        posts = new ArrayList<Posts>();
        comments = new ArrayList<Comments>();
        final RequestQueue queue = Volley.newRequestQueue(this);
        String postsUrl = "http://jsonplaceholder.typicode.com/posts";
        String commentsUrl = "http://jsonplaceholder.typicode.com/comments";
        JSONObject jsonb = new JSONObject();


        final JsonObjectRequest jsonPostRequest = new JsonObjectRequest
                (Request.Method.POST, postsUrl, jsonb, new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response){
                        txtString.setText(response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        txtString.setText(error.toString());
                    }
                });


        final JsonArrayRequest postsArrayRequest = new JsonArrayRequest(Request.Method.GET, postsUrl,
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


        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = txtId.getText().toString();
                postUrl = "http://jsonplaceholder.typicode.com/posts/"+id;
                commentUrl = "http://jsonplaceholder.typicode.com/comments/"+id;
                final StringRequest postStringRequest = new StringRequest(Request.Method.GET, postUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                txtString.setText("Post:\n" + response);
                                //posts.add(createPost(response));
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        txtString.setText(error.toString());
                    }
                });

                final StringRequest commentStringRequest = new StringRequest(Request.Method.GET, commentUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                txtString2.setText("Comment:\n" + response);
                                //Comment comment = createComment(response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        txtString2.setText(error.toString());
                    }
                });
                queue.add(postStringRequest);
                queue.add(commentStringRequest);
                queue.add(jsonPostRequest);
            }
        });

        btnSyncPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Synchronizing Posts..", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                ph.open();
                ph.deleteAllPosts();
                ph.close();
                queue.add(postsArrayRequest);
                Snackbar.make(view, "Posts successfully synchronized.", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
            }
        });

        btnSyncComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Synchronizing Comments..", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                ch.open();
                ch.deleteAllComments();
                ch.close();
                queue.add(commentsArrayRequest);
                Snackbar.make(view, "Comments successfully synchronized.", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
            }
        });

        btnViewPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PostsActivity.class);
                ph.open();
                intent.putExtra("Parcel", ph.getAllPosts());
                ph.close();
                startActivity(intent);
            }
        });

        btnViewComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CommentsActivity.class);
                ch.open();
                intent.putExtra("Parcel", ch.getAllComments());
                ch.close();
                startActivity(intent);
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ch.open();
                ph.open();
                ph.deleteAllPosts();
                ch.deleteAllComments();
                ch.close();
                ph.close();
                Snackbar.make(view, "Data has been cleared.", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
            }
        });

        btnList.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), ExpandableListActivity.class);
                ph.open();
                ch.open();
                posts = ph.getAllPosts();
                comments = ch.getAllComments();
                ph.close();
                ch.close();
                intent.putExtra("Posts", posts);
                intent.putExtra("Comments", comments);
                startActivity(intent);
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
            post = ph.addPost(userId, id, title, body);
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


