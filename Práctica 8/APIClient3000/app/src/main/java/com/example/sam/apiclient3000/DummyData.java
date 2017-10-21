package com.example.sam.apiclient3000;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.sam.apiclient3000.Adapters.PostAdapter;
import com.example.sam.apiclient3000.Models.Posts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Sam on 20/10/2017.
 */

public class DummyData extends AppCompatActivity {
    PostAdapter postAdapter;
    ListView listView;
    ArrayList<Posts> posts;

    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> oCommentsPost = new ArrayList<String>();
        oCommentsPost.add("Sam 1");
        oCommentsPost.add("Sam 2");

        List<String> oCommentsPost2 = new ArrayList<String>();
        oCommentsPost2.add("b013fcf5cf515aa3");

        expandableListDetail.put("Padre 1", oCommentsPost);
        expandableListDetail.put("Padre 2", oCommentsPost2);

        return expandableListDetail;
    }
}
