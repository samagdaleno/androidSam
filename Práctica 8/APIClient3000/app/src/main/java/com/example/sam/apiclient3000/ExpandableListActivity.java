package com.example.sam.apiclient3000;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.sam.apiclient3000.Adapters.CustomExpandableListAdapter;
import com.example.sam.apiclient3000.Models.Comments;
import com.example.sam.apiclient3000.Models.Posts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class ExpandableListActivity extends AppCompatActivity {
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    TreeMap<String, List<String>> expandableListDetail;
    ArrayList<Posts> posts;
    ArrayList<Comments> comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list);

        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);

        posts = this.getIntent().getParcelableArrayListExtra("Posts");
        comments = this.getIntent().getParcelableArrayListExtra("Comments");
        expandableListDetail = getData(comments, posts);

        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        expandableListTitle.get(groupPosition)
                                + " -> "
                                + expandableListDetail.get(
                                expandableListTitle.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT
                ).show();
                return false;
            }
        });
    }

    public TreeMap<String, List<String>> getData(ArrayList<Comments> comments, ArrayList<Posts> posts) {
        TreeMap<String, List<String>> expandableListDetail = new TreeMap<String, List<String>>();


        for(Posts post : posts){
            List<String> oCommentsPost = new ArrayList<String>();
            String postInfo = post.getTitle() + " (ID: " + post.getId() + ", User ID: " + post.getUserId() + ")";
            for (Comments comment : comments){
                if(comment.getPostid().equals(post.getId())){
                    String commentString = comment.getName() + ":\n" + comment.getBody();
                    oCommentsPost.add(commentString);
                }
            }
            expandableListDetail.put(postInfo, oCommentsPost);

        }

        return expandableListDetail;
    }

    public void onBackPressed() {
        finish();
    }
}
