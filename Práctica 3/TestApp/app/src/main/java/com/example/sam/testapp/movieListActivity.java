package com.example.sam.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.sam.testapp.Models.Movie;
import com.example.sam.testapp.Models.MovieAdapter;

import java.util.ArrayList;


public class movieListActivity extends AppCompatActivity {
    MovieAdapter oMovieAdapter;
    ListView oListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        oListView=(ListView) findViewById(R.id.lv_movieList);
        oMovieAdapter=new MovieAdapter(this);
        oListView.setAdapter(oMovieAdapter);


        ArrayList<Movie> movieArray = new ArrayList<Movie>();
        Movie newMovie = new Movie(
                "Sam Test",
                "121 Min",
                "Sam Magdaleno",
                "New Wave",
                "05/08/54"
        );

        movieArray.add(newMovie);
        fillMovieDatabase(movieArray);
    }



    private void fillMovieDatabase(ArrayList<Movie> lMovies)
    {
        oMovieAdapter.clear();

        for(Movie oMovie: lMovies)
        {
            oMovieAdapter.add(oMovie);
        }

        oMovieAdapter.notifyDataSetChanged();;
    }
}
