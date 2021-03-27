package com.simpla.movielist.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieModel implements Parcelable {

    private String title;
    private String poster_path;
    private String release_date;
    private int id;
    private boolean adult;
    private String original_language;
    private String overview;
    private float vote_average;
    private int runtime;

    public MovieModel(String title, String poster_path, String release_date, int id, boolean adult, String original_language, String overview, float vote_average,int runtime) {
        this.title = title;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.id = id;
        this.adult = adult;
        this.original_language = original_language;
        this.overview = overview;
        this.vote_average = vote_average;
        this.runtime = runtime;
    }

    protected MovieModel(Parcel in) {
        title = in.readString();
        poster_path = in.readString();
        release_date = in.readString();
        id = in.readInt();
        adult = in.readByte() != 0;
        original_language = in.readString();
        overview = in.readString();
        vote_average = in.readFloat();
        runtime = in.readInt();
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    public int getRuntime() {
        return runtime;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public int getId() {
        return id;
    }

    public boolean isAdult() {
        return adult;
    }


    public String getOriginal_language() {
        return original_language;
    }


    public String getOverview() {
        return overview;
    }


    public float getVote_average() {
        return vote_average;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(poster_path);
        parcel.writeString(release_date);
        parcel.writeInt(id);
        parcel.writeByte((byte) (adult ? 1 : 0));
        parcel.writeString(original_language);
        parcel.writeString(overview);
        parcel.writeFloat(vote_average);
        parcel.writeInt(runtime);
    }
}
