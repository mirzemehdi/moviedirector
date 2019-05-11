package com.example.lenovo.moviedirector;


import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import Constants.Constants;
import dmax.dialog.SpotsDialog;


public class MovieDetailsActivity extends AppCompatActivity {

   private RequestQueue queue;
   private TextView title,year,released,runtime,genre,director,writer,actors,plot,language,
    country,awards,imdbRating,imdbVotes,type;
   private String URL;
   private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        URL=Constants.URL_DETAILS_LEFT+getIntent().getStringExtra("id")+Constants.URL_DETAILS_RIGHT;
        queue= Volley.newRequestQueue(this);

        alertDialog=new SpotsDialog.Builder().setContext(this).build();
        alertDialog.setCancelable(false);
        alertDialog.show();
        title=(TextView)findViewById(R.id.titleD);
        year=(TextView)findViewById(R.id.yearD);
        released=(TextView)findViewById(R.id.releasedD);
        runtime=(TextView)findViewById(R.id.runtimeD);
        genre=(TextView)findViewById(R.id.genreD);
        director=(TextView)findViewById(R.id.directorD);
        writer=(TextView)findViewById(R.id.writerD);
        actors=(TextView)findViewById(R.id.actorsD);
        plot=(TextView)findViewById(R.id.plotD);
        language=(TextView)findViewById(R.id.languageD);
        country=(TextView)findViewById(R.id.countryD);
        awards=(TextView)findViewById(R.id.awardsD);
        imdbRating=(TextView)findViewById(R.id.imdbRatingD);
        imdbVotes=(TextView)findViewById(R.id.imdbVotesD);
        type=(TextView)findViewById(R.id.typeD);









        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, URL,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            title.setText(response.getString("Title"));
                            year.setText(Html.fromHtml("<b>Year: </b>"    +response.getString("Year")));
                            released.setText(Html.fromHtml("<b>Released: </b>"    +response.getString("Released")));
                            runtime.setText(Html.fromHtml("<b>Runtime: </b>"    +response.getString("Runtime")));
                            genre.setText(Html.fromHtml("<b>Genre: </b>"    +response.getString("Genre")));
                            director.setText(Html.fromHtml("<b>Director: </b>"    +response.getString("Director")));
                            writer.setText(Html.fromHtml("<b>Writer: </b>"    +response.getString("Writer")));
                            actors.setText(Html.fromHtml("<b>Actors: </b>"    +response.getString("Actors")));
                            plot.setText(Html.fromHtml("<b>Plot: </b>"    +response.getString("Plot")));
                            language.setText(Html.fromHtml("<b>Language: </b>"    +response.getString("Language")));
                            country.setText(Html.fromHtml("<b>Country: </b>"    +response.getString("Country")));
                            awards.setText(Html.fromHtml("<b>Awards: </b>"    +response.getString("Awards")));
                            imdbRating.setText(Html.fromHtml("<b>imdbRating: </b>"    +response.getString("imdbRating")));
                            imdbVotes.setText(Html.fromHtml("<b>imdbVotes: </b>"    +response.getString("imdbVotes")));
                            type.setText(Html.fromHtml("<b>Type: </b>"    +response.getString("Type")));
                            alertDialog.dismiss();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(request);
    }
}
