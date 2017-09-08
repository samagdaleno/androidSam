package com.example.sam.testapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.sam.testapp.Models.Movie;
import com.example.sam.testapp.Models.MovieAdapter;
import com.example.sam.testapp.Models.MySingleton;

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

        oListView.setClickable(true);
        oListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                AlertDialog.Builder adb=new AlertDialog.Builder(movieListActivity.this);
                adb.setTitle("Eliminar.");
                adb.setMessage("¿Estás seguro?");
                final int positionToRemove = position;
                adb.setNegativeButton("Cancelar", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        MySingleton.getInstance().getMovies().remove(positionToRemove);
                        updateMovieList(MySingleton.getInstance().getMovies());
                    }});
                adb.show();
                oMovieAdapter.notifyDataSetChanged();
            }
        });

        ArrayList<Movie> movieArray = MySingleton.getInstance().getMovies();
        fillMovieDatabase(movieArray);


    }

    @Override
    public void onBackPressed() {
        retorno();
    }

    public void retorno() {
        Intent intent = new Intent();
        intent.putExtra("resultado",1);
        setResult(Activity.RESULT_OK, intent);
        finish();
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

    public void updateMovieList (ArrayList<Movie> lMovies) {
        oMovieAdapter.clear();

        for(Movie oMovie: lMovies)
        {
            oMovieAdapter.add(oMovie);
        }

        oMovieAdapter.notifyDataSetChanged();
    }
}
