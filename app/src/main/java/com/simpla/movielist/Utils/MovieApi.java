package com.simpla.movielist.Utils;

import com.simpla.movielist.Models.MovieModel;
import com.simpla.movielist.Response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {

    @GET("/3/search/movie")
    Call<MovieSearchResponse> searchMovie(
            @Query("api_key") String key,
            @Query("query") String query,
            @Query("page") String page
    );

    //Making search with id
    @GET("/3/movie/{movie_id}?")
    Call<MovieModel> getMovie(
         @Path("movie_id") int id,
         @Query("api_key") String key
    );

}
