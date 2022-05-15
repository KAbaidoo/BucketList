package com.example.bucketlist;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView eventTitle = findViewById(R.id.textView_detail_title);
        eventTitle.setText(getIntent().getStringExtra("title"));

    }
}