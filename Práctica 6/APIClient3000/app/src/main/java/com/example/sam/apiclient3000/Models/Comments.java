package com.example.sam.apiclient3000.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Sam on 06/10/2017.
 */

public class Comments implements Parcelable {
    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    private String postid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    private String body;

    public Comments(){}

    public Comments(String postid, String id, String name, String body, String email){
        this.postid = postid;
        this.id = id;
        this.name = name;
        this.body = body;
        this.email = email;
    }

    public Comments(Parcel in) {
        this.postid = in.readString();
        this.id = in.readString();
        this.name = in.readString();
        this.email = in.readString();
        this.body = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(postid);
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(body);
        dest.writeString(email);
    }

    public static final Creator<Comments> CREATOR = new Creator<Comments>() {
        @Override
        public Comments createFromParcel(Parcel in) {
            return new Comments(in);
        }

        @Override
        public Comments[] newArray(int size) {
            return new Comments[size];
        }
    };

}
