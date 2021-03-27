package com.simpla.movielist.Repositories;

import androidx.lifecycle.LiveData;

import com.simpla.movielist.Models.MovieModel;
import com.simpla.movielist.Request.MovieApiClient;

import java.util.List;

public class MovieRepository {

    private static MovieRepository instance;

    private MovieApiClient movieApiClient;

    public static MovieRepository getInstance(){
        if(instance == null) instance = new MovieRepository();
        return instance;
    }

    private MovieRepository(){
        movieApiClient = MovieApiClient.getInstance();
    }

    public LiveData<List<MovieModel>> getMovies(){
        return movieApiClient.getMovies();
    }

    public void searchMovieApi(String query,int pageNumber){
        movieApiClient.searchMoviesApi(query,pageNumber);
    }
}
