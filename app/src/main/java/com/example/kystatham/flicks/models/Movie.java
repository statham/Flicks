package com.example.kystatham.flicks.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by kystatham on 9/12/17.
 */

public class Movie {

    public enum Popularity {
        POPULAR, UNPOPULAR
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath);
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public Popularity getPopularity() { return popularity; }

    public String getReleaseDate() { return releaseDate; }

    public float getVoteAverage() { return voteAverage; }

    String posterPath;
    String backdropPath;
    String originalTitle;
    String overview;
    Popularity popularity;
    String releaseDate;
    float voteAverage;

    public Movie(JSONObject jsonObject) throws JSONException {
        this.posterPath = jsonObject.getString("poster_path");
        this.backdropPath = jsonObject.getString("backdrop_path");
        this.originalTitle = jsonObject.getString("original_title");
        this.overview = jsonObject.getString("overview");
        this.voteAverage = (float) jsonObject.getDouble("vote_average");
        if (voteAverage >= 5.0) {
            this.popularity = Popularity.POPULAR;
        } else {
            this.popularity = Popularity.UNPOPULAR;
        }
        this.releaseDate = jsonObject.getString("release_date");
    }

    public static ArrayList<Movie> fromJSONArray(JSONArray array) {
        ArrayList<Movie> results = new ArrayList<>();

        for(int x = 0; x < array.length(); x++) {
            try {
                results.add(new Movie(array.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }
}
