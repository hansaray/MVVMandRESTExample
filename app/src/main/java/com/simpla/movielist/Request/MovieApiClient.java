package com.simpla.movielist.Request;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.simpla.movielist.AppExecutors;
import com.simpla.movielist.Models.MovieModel;
import com.simpla.movielist.Response.MovieSearchResponse;
import com.simpla.movielist.Utils.Credentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class MovieApiClient {

    private MutableLiveData<List<MovieModel>> mMovies;
    private static MovieApiClient instance;

    //Global Runnable
    private RetrieveMoviesRunnable retrieveMoviesRunnable;

    public static MovieApiClient getInstance(){
        if(instance == null) instance = new MovieApiClient();
        return instance;
    }

    private MovieApiClient(){
        mMovies = new MutableLiveData<>();
    }

    public LiveData<List<MovieModel>> getMovies() {
        return mMovies;
    }

    public void searchMoviesApi(String query,int pageNumber){
        if(retrieveMoviesRunnable != null){
            retrieveMoviesRunnable = null;
        }

        retrieveMoviesRunnable = new RetrieveMoviesRunnable(query,pageNumber);

        if(AppExecutors.getInstance() == null) return;
        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveMoviesRunnable);

        AppExecutors.getInstance().networkIO().schedule(() -> {
            //Cancelling the retrofit call
            myHandler.cancel(true);
        },3000, TimeUnit.MILLISECONDS);

    }

    //Retrieving data from RestApi
    private class RetrieveMoviesRunnable implements Runnable{

        private String query;
        private int pageNumber;
        boolean cancel;

        public RetrieveMoviesRunnable(String query, int pageNumber) {
            this.query = query;
            this.pageNumber = pageNumber;
            this.cancel = false;
        }

        @Override
        public void run() {
            try {
                Response response = getMovies(query,pageNumber).execute();
                if(cancel) return;
                if(response.code() == 200){
                    List<MovieModel> list = new ArrayList<>(((MovieSearchResponse)response.body()).getMovies());
                    if(pageNumber == 1){
                        //Sending data to live data
                        mMovies.postValue(list);
                    }else{
                        List<MovieModel> currentMovies = mMovies.getValue();
                        currentMovies.addAll(list);
                        mMovies.postValue(currentMovies);
                    }
                }else{
                    String error = response.errorBody().string();
                    Log.e("try","Error " +error);
                    mMovies.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mMovies.postValue(null);
            }
        }

        private Call<MovieSearchResponse> getMovies(String query,int pageNumber){
            return Service.getMovieApi().searchMovie(
                    Credentials.API_KEY,query,String.valueOf(pageNumber));
        }

        private void cancelRequest(){
            Log.e("try","Cancelling search request");
            cancel = true;
        }
    }
}
