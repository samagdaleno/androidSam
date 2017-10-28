package com.example.sam.myapplication.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Sam on 27/10/2017.
 */

public class Ladder implements Parcelable{
    private String board_id;
    private String begin;
    private String destination;

    public String getBoard_id() {
        return board_id;
    }

    public void setBoard_id(String board_id) {
        this.board_id = board_id;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Ladder(){}

    public Ladder(String board_id, String begin, String destination){
        this.board_id = board_id;
        this.begin = begin;
        this.destination = destination;
    }

    public Ladder(Parcel in) {
        this.board_id = in.readString();
        this.begin = in.readString();
        this.destination = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(board_id);
        dest.writeString(begin);
        dest.writeString(destination);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        @Override
        public Ladder createFromParcel(Parcel in){
            return new Ladder(in);
        }
        @Override
        public Ladder[] newArray(int size){
            return new Ladder[size];
        }
    };
}
