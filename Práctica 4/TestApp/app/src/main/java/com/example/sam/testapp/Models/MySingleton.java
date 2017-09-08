package com.example.sam.testapp.Models;

import java.util.ArrayList;

/**
 * Created by Sam on 07/09/2017.
 */

public final class MySingleton {
    private static final MySingleton SELF = new MySingleton();

    private ArrayList<Movie> movies = new ArrayList<Movie>();

    private MySingleton() {
        // Don't want anyone else constructing the singleton.
    }

    public static MySingleton getInstance() {
        return SELF;
    }

    public  ArrayList<Movie> getMovies() {
        return movies;
    }
}
