package com.simpla.movielist.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.simpla.movielist.Models.MovieModel;

public class MovieResponse { //single movie request

    @SerializedName("results")
    @Expose
    private MovieModel model;

    private MovieModel getMovie(){
        return model;
    }
}
