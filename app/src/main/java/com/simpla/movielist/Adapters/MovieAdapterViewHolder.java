package com.simpla.movielist.Adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.simpla.movielist.R;

public class MovieAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView title,details;
    ImageView image;
    RatingBar rating;

    OnMovieListener onMovieListener;


    public MovieAdapterViewHolder(@NonNull View itemView, OnMovieListener onMovieListener) {
        super(itemView);
        this.onMovieListener = onMovieListener;
        title = itemView.findViewById(R.id.im_title);
        details = itemView.findViewById(R.id.im_details);
        image = itemView.findViewById(R.id.im_image);
        rating = itemView.findViewById(R.id.im_rating);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onMovieListener.onMovieClick(getAdapterPosition());
    }
}
