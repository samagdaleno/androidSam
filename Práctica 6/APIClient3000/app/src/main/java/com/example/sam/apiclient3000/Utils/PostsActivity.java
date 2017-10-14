package com.example.sam.apiclient3000.Utils;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.example.sam.apiclient3000.Adapters.PostAdapter;
import com.example.sam.apiclient3000.Models.Posts;
import com.example.sam.apiclient3000.R;

import java.util.ArrayList;

public class PostsActivity extends AppCompatActivity {
    PostAdapter postAdapter;
    ListView listView;
    ArrayList<Posts> posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);
        listView = (ListView) findViewById(R.id.listView);
        postAdapter = new PostAdapter(this);
        listView.setAdapter(postAdapter);

        posts = this.getIntent().getParcelableArrayListExtra("Parcel");
        fillList(posts);
    }

    public void fillList(ArrayList<Posts> posts) {
        for (Posts post : posts) {
            postAdapter.add(post);
        }
        postAdapter.notifyDataSetChanged();
    }

    public void onBackPressed() {
        finish();
    }

}
