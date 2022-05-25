package com.example.bucketlist;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView eventTitle = (TextView) findViewById(R.id.textView_detail_title);
        TextView eventPrice = (TextView) findViewById(R.id.detail_price);
        TextView description = (TextView) findViewById(R.id.textView_description);
        ImageView imgBanner = (ImageView) findViewById(R.id.imageView_detail);


        eventTitle.setText(getIntent().getStringExtra("title"));
        eventPrice.setText(getIntent().getStringExtra("price"));
        Glide.with(getApplicationContext()).load(getIntent().getStringExtra("image_resource")).into(imgBanner);

    }
}