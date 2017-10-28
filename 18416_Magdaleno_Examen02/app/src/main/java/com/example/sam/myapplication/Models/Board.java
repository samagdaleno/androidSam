package com.example.sam.myapplication.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Sam on 27/10/2017.
 */

public class Board implements Parcelable {
    private String id;
    private String name;
    private String author;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Board(){}

    public Board(String id, String name, String author){
        this.id = id;
        this.name = name;
        this.author = author;
    }

    public Board(Parcel in){
        this.id = in.readString();
        this.name = in.readString();
        this.author = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(author);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        @Override
        public Board createFromParcel(Parcel in){
            return new Board(in);
        }
        @Override
        public Board[] newArray(int size){
            return new Board[size];
        }
    };
}
