package com.example.lenovo.moviedirector;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import Adapter.MyAdapter;
import Constants.Constants;
import Movie.Movie;
import SharedPreferences.Prefs;

public class MainActivity extends AppCompatActivity {

    //TODO URL=http://www.omdbapi.com/?s=batman&apikey=2afbb96c;

    private RecyclerView movieRecyclerView;
    private RecyclerView.Adapter adapter;
    private List<Movie> movieList;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                searchAlert();
            }
        });



        //Setup adapter
        queue= Volley.newRequestQueue(this);
        movieList=new ArrayList<>();
        Prefs prefs=new Prefs(MainActivity.this);
        getMovieList(prefs.getPrefs());
        movieRecyclerView=(RecyclerView)findViewById(R.id.movieRecyclerView);
        movieRecyclerView.setHasFixedSize(true);
        movieRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new MyAdapter(this,movieList);
        movieRecyclerView.setAdapter(adapter);

    }


    /*
    * This method updates movieList to use JSON object(Array) (VolleyLibrary)
    *
    * */
    public List<Movie> getMovieList(String search){
        movieList.clear();

        //TODO write JSON codes to return movieArray
        String URL=Constants.URL_LEFT+search+Constants.URL_RIGHT;
        JsonObjectRequest searchObject=new JsonObjectRequest(Request.Method.GET, URL, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {



                    try {
                        JSONArray array = response.getJSONArray("Search");

                        for (int i=0;i<array.length();i++){
                            Movie movie = new Movie();
                            movie.setTitle(array.getJSONObject(i).getString("Title"));
                            movie.setYear(array.getJSONObject(i).getString("Year"));
                            movie.setImdbId(array.getJSONObject(i).getString("imdbID"));
                            movie.setPoster(array.getJSONObject(i).getString("Poster"));
                            movie.setType(array.getJSONObject(i).getString("Type"));

                            movieList.add(movie);

                        }
                        adapter.notifyDataSetChanged();

                    }


                    catch (JSONException e) {
                        e.printStackTrace();
                    }





            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error",error.getMessage());
            }
        });

        queue.add(searchObject);


        return movieList;
    }

    public void searchAlert(){


        View view= LayoutInflater.from(this).inflate(R.layout.search_alert,null);
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setView(view);
        final AlertDialog dialog=builder.create();
        dialog.show();
        final EditText searchEditText=(EditText)view.findViewById(R.id.searchEditText);
        Button searchButton=(Button)view.findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search=searchEditText.getText().toString();
                Prefs prefs=new Prefs(MainActivity.this);
                prefs.setPrefs(search);
                getMovieList(search);
                dialog.dismiss();
            }
        });


    }















    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.search) {
            searchAlert();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
