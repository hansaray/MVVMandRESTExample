package com.simpla.movielist.Request;

import com.simpla.movielist.Utils.Credentials;
import com.simpla.movielist.Utils.MovieApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(Credentials.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    private static MovieApi movieApi = retrofit.create(MovieApi.class);

    public static MovieApi getMovieApi(){
        return movieApi;
    }
}
