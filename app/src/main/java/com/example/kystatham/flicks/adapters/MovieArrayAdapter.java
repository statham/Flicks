package com.example.kystatham.flicks.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kystatham.flicks.R;
import com.example.kystatham.flicks.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by kystatham on 9/12/17.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    private static class ViewHolder {
        TextView title;
        TextView overview;
        ImageView image;
        ImageView backdrop;
    }

    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, 0, movies);
    }

    // Returns the number of types of Views that will be created by getView(int, View, ViewGroup)
    @Override
    public int getItemViewType(int position) {
        return getItem(position).getPopularity().ordinal();
    }

    @Override
    public int getViewTypeCount() {
        return Movie.Popularity.values().length;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //get the data item for this position
        Movie movie = getItem(position);

        //check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; //view lookup cache stored in tag
        int type = getItemViewType(position);
        if (convertView == null) {
            //if there's no view to reuse, inflate the new view for the row
            convertView = getInflatedLayoutForType(type);

            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.overview = (TextView) convertView.findViewById(R.id.tvOverview);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.ivMovieImage);
            viewHolder.backdrop = (ImageView) convertView.findViewById(R.id.ivBackdrop);

            //cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            //view is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //Populate the data from the data object via the viewHolder object
        //into the template view
        if(type == Movie.Popularity.UNPOPULAR.ordinal()) {
            viewHolder.image.setImageResource(0);
            viewHolder.title.setText(movie.getOriginalTitle());
            viewHolder.overview.setText(movie.getOverview());
            int orientation = getContext().getResources().getConfiguration().orientation;
            String movieImage;
            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                movieImage = movie.getPosterPath();
            } else {
                movieImage = movie.getBackdropPath();
            }
            Picasso.with(getContext()).load(movieImage)
                    .fit().centerInside()
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(viewHolder.image);
        } else {
            viewHolder.backdrop.setImageResource(0);
            Picasso.with(getContext()).load(movie.getBackdropPath())
                    .fit().centerInside()
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(viewHolder.backdrop);
        }

        return convertView;
    }

    private View getInflatedLayoutForType(int type) {
        if(type == Movie.Popularity.POPULAR.ordinal()) {
            return LayoutInflater.from(getContext()).inflate(R.layout.item_popular_movie, null);
        } else if (type == Movie.Popularity.UNPOPULAR.ordinal()) {
            return LayoutInflater.from(getContext()).inflate(R.layout.item_movie, null);
        } else {
            return null;
        }
    }
}
