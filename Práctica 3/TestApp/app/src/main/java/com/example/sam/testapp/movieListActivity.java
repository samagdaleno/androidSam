package com.example.sam.testapp;

import android.content.Intent;
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
        Intent intent = getIntent();

        ArrayList<Movie> movieArray = (ArrayList<Movie>)getIntent().getSerializableExtra("movieList");
        //ArrayList<Movie> movieArray = new ArrayList<Movie>();
        fillMovieDatabase(movieArray);
    }



    private void fillMovieDatabase(ArrayList<Movie> lMovies)
    {
        //oMovieAdapter.clear();

        for(Movie oMovie: lMovies)
        {
            oMovieAdapter.add(oMovie);
        }

        oMovieAdapter.notifyDataSetChanged();;
    }
}
