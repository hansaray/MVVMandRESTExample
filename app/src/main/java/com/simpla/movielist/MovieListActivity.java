package com.simpla.movielist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;

import com.simpla.movielist.Adapters.MovieAdapter;
import com.simpla.movielist.Adapters.OnMovieListener;
import com.simpla.movielist.Models.MovieModel;
import com.simpla.movielist.ViewModels.MovieListViewModel;

import java.util.List;

public class MovieListActivity extends AppCompatActivity implements OnMovieListener {

    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private MovieListViewModel movieListViewModel;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.list_rw);
        searchView = findViewById(R.id.list_search);
        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);
        ObserveAnyChange();
        setupRw();
        setupSearch();

    }

    private void setupSearch() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchMoviesApi(s,1);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return true;
            }
        });
    }

    private void ObserveAnyChange(){
        movieListViewModel.getMovies().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                if(movieModels != null){
                    for(MovieModel m: movieModels){
                        Log.e("try",m.getTitle());
                        adapter.setList(movieModels);
                    }
                }
            }
        });
    }

    private void searchMoviesApi(String query,int pageNumber){
        movieListViewModel.searchMovieApi(query,pageNumber);
    }

    private void setupRw(){
        adapter = new MovieAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onMovieClick(int position) {

    }

    /*private void getRetrofitResponse() {
        MovieApi movieApi = Service.getMovieApi();
        Call<MovieSearchResponse> responseCall = movieApi
                .searchMovie(Credentials.API_KEY,"Action","1");
        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                if(response.code() == 200){
                    Log.e("try","the response" + response.body().toString());
                    List<MovieModel> list = new ArrayList<>(response.body().getMovies());
                    for(MovieModel m: list) {
                        Log.e("try", "The list" + m.getRelease_date());
                    }
                }else{
                    try {
                        Log.e("try","Error" + response.errorBody().toString());
                    } catch (Exception e) {
                        Log.e("try","clickError");
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {
                Log.e("try",t.getMessage());
            }
        });
    }

    private void getRetrofitResponseID(){
        MovieApi movieApi = Service.getMovieApi();
        Call<MovieModel> responseCall = movieApi.getMovie(550,Credentials.API_KEY);
        responseCall.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                if(response.code() == 200){
                    MovieModel model = response.body();
                    Log.e("try","the response" + model.getTitle());
                }else{
                    try {
                        Log.e("try","Error" + response.errorBody().toString());
                    } catch (Exception e) {
                        Log.e("try","clickError");
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                Log.e("try",t.getMessage());
            }
        });
    }*/
}