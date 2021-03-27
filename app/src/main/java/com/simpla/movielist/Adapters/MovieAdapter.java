package com.simpla.movielist.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.simpla.movielist.Models.MovieModel;
import com.simpla.movielist.R;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MovieModel> list;
    private OnMovieListener onMovieListener;

    public MovieAdapter(OnMovieListener onMovieListener) {
        this.onMovieListener = onMovieListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie,parent,false);
        return new MovieAdapterViewHolder(v,onMovieListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MovieAdapterViewHolder)holder).title.setText(list.get(position).getTitle());
        ((MovieAdapterViewHolder)holder).details.setText(list.get(position).getRuntime());

        ((MovieAdapterViewHolder)holder).rating.setRating((list.get(position).getVote_average())/2);

        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w500/"+list.get(position).getPoster_path())
                .into(((MovieAdapterViewHolder)holder).image);
    }

    @Override
    public int getItemCount() {
        if(list != null) return list.size();
        return 0;
    }

    public void setList(List<MovieModel> list) {
        this.list = list;
    }
}
