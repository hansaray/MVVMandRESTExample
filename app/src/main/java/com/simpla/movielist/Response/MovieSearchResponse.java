package com.simpla.movielist.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.simpla.movielist.Models.MovieModel;

import java.util.List;

public class MovieSearchResponse { //get multiple popular movies

    @SerializedName("total_results")
    @Expose
    private int total_count;

    @SerializedName("results")
    @Expose
    private List<MovieModel> list;

    public int getTotal_count(){
        return  total_count;
    }

    public List<MovieModel> getMovies(){
        return list;
    }

    @Override
    public String toString() {
        return "MovieSearchResponse{" +
                "total_count=" + total_count +
                ", list=" + list +
                '}';
    }
}
