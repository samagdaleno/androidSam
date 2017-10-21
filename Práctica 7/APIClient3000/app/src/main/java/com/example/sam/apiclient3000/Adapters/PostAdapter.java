package com.example.sam.apiclient3000.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sam.apiclient3000.Models.Posts;
import com.example.sam.apiclient3000.R;

/**
 * Created by Sam on 13/10/2017.
 */

public class PostAdapter extends ArrayAdapter<Posts> {

    public PostAdapter(Context context) {
        super(context, R.layout.post_list, R.id.textInfo);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View objectView = super.getView(position, convertView, parent);
        TextView txtInfo = (TextView) objectView.findViewById(R.id.textInfo);

        final Posts post = this.getItem(position);
        txtInfo.setText(post.getTitle() + "\n" + post.getBody());

        return objectView;
    }
}