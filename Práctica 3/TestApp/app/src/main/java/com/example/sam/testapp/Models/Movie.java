package com.example.sam.testapp.Models;

import java.io.Serializable;

/**
 * Created by Sam on 25/08/2017.
 */

public class Movie implements Serializable {

    public Movie(String movieName, String runtime, String director, String genre, String releaseDate) {
        this.movieName = movieName;
        this.runtime = runtime;
        this.director = director;
        this.genre = genre;
        this.releaseDate = releaseDate;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    private String movieName;

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    private String runtime;

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    private String director;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    private String genre;

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    private String releaseDate;
}
