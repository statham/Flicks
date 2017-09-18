package com.example.kystatham.flicks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class SingleMovie extends AppCompatActivity {

    TextView tvTitle;
    TextView tvSynopsis;
    TextView tvReleaseDate;
    ImageView ivBackdrop;
    RatingBar rbRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_movie);

        String title = getIntent().getStringExtra("title");
        String releaseDate = getIntent().getStringExtra("releaseDate");
        String synopsis = getIntent().getStringExtra("synopsis");
        String backdropPath = getIntent().getStringExtra("backdropPath");
        float voteAverage = getIntent().getFloatExtra("voteAverage", 0);

        tvTitle = (TextView) findViewById(R.id.tvSingleTitle);
        tvSynopsis = (TextView) findViewById(R.id.tvSynopsis);
        tvReleaseDate = (TextView) findViewById(R.id.tvReleaseDate);
        ivBackdrop = (ImageView) findViewById(R.id.ivSingleBackdrop);
        rbRating = (RatingBar) findViewById(R.id.rbSingle);

        tvTitle.setText(title);
        tvSynopsis.setText(synopsis);
        tvReleaseDate.setText(String.format("Release Date: %s", releaseDate));

        ivBackdrop.setImageResource(0);
        Picasso.with(SingleMovie.this).load(backdropPath)
                .fit().centerInside()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(ivBackdrop);

        rbRating.setRating(voteAverage);
    }
}
