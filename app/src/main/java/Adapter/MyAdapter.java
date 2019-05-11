package Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.moviedirector.MovieDetailsActivity;
import com.example.lenovo.moviedirector.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import Movie.Movie;

public class MyAdapter  extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private Context context;
    private List<Movie> movieList;

    public MyAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }




    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_row,parent,false);

        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {

        holder.movieTitle.setText(movieList.get(position).getTitle());
        holder.movieType.setText("Type: "+movieList.get(position).getType());
        holder.movieYear.setText("Year: "+movieList.get(position).getYear());
        holder.movieId.setText("imdbID: "+movieList.get(position).getImdbId());
        Picasso.get().load(movieList.get(position).getPoster())
                .placeholder(android.R.drawable.ic_menu_gallery).into(holder.movieImage);

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       private ImageView movieImage;
       private TextView movieTitle,movieId,movieYear,movieType;
        public ViewHolder(final View itemView, Context ctx) {

            super(itemView);
            context=ctx;
            movieImage=(ImageView)itemView.findViewById(R.id.image);
            movieTitle=(TextView)itemView.findViewById(R.id.id);
            movieId=(TextView)itemView.findViewById(R.id.title);
            movieYear=(TextView)itemView.findViewById(R.id.year);
            movieType=(TextView)itemView.findViewById(R.id.type);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, MovieDetailsActivity.class);
                    intent.putExtra("id",movieList.get(getAdapterPosition()).getImdbId());
                    context.startActivity(intent);
                }
            });

        }
    }
}
