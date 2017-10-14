package com.example.sam.apiclient3000;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.example.sam.apiclient3000.Adapters.CommentAdapter;
import com.example.sam.apiclient3000.Models.Comments;

import java.util.ArrayList;

public class CommentsActivity extends AppCompatActivity {

    CommentAdapter commentAdapter;
    ListView listView;
    ArrayList<Comments> comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        listView = (ListView) findViewById(R.id.listView);
        commentAdapter = new CommentAdapter(this);
        listView.setAdapter(commentAdapter);

        comments = this.getIntent().getParcelableArrayListExtra("Parcel");
        fillList(comments);
    }

    public void fillList(ArrayList<Comments> comments) {
        for (Comments comment : comments) {
            commentAdapter.add(comment);
        }
        commentAdapter.notifyDataSetChanged();
    }

    public void onBackPressed() {
        finish();
    }

}
