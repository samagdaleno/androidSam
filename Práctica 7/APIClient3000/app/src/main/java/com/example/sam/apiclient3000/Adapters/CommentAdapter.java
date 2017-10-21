package com.example.sam.apiclient3000.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sam.apiclient3000.Models.Comments;
import com.example.sam.apiclient3000.R;

/**
 * Created by Sam on 13/10/2017.
 */

public class CommentAdapter extends ArrayAdapter<Comments> {

    public CommentAdapter(Context context) {
        super(context, R.layout.comment_list, R.id.textInfo);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View objectView = super.getView(position, convertView, parent);
        TextView txtInfo = (TextView) objectView.findViewById(R.id.textInfo);

        final Comments comment = this.getItem(position);
        txtInfo.setText(comment.getBody());

        return objectView;
    }
}