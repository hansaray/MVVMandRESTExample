package com.simpla.movielist.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.simpla.movielist.Models.MovieModel;
import com.simpla.movielist.Repositories.MovieRepository;

import java.util.List;

public class MovieListViewModel extends ViewModel {

    private MovieRepository movieRepository;

    public MovieListViewModel() {
        movieRepository = MovieRepository.getInstance();
    }

    public LiveData<List<MovieModel>> getMovies(){
        return movieRepository.getMovies();
    }

    public void searchMovieApi(String query,int pageNumber){
        movieRepository.searchMovieApi(query,pageNumber);
    }
}
