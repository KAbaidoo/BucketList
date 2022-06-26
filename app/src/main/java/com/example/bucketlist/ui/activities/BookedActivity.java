package com.example.bucketlist.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bucketlist.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class BookedActivity extends AppCompatActivity {
    FirebaseFirestore db;
    CollectionReference userListRef;
    CollectionReference eventsRef;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked);


        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        String uid = user.getUid();
        String email = user.getEmail();

        userListRef = db.collection("users").document(uid).collection("list");
        eventsRef = db.collection("events");

        Intent intent = getIntent();
        String eventId = intent.getStringExtra("id");
//        loadEvent(eventId);
//        Grab textViews
        TextView eventTitle = findViewById(R.id.textView_detail_title);

        TextView description = findViewById(R.id.textView_description);
//        ImageView imgBanner = findViewById(R.id.imageView_detail);
        RatingBar ratingBar = findViewById(R.id.ratingBar_detail);


//        Set TextViews
        eventTitle.setText(intent.getStringExtra("title"));
        description.setText(intent.getStringExtra("info"));
        Float price = intent.getFloatExtra("price", 0);
//        eventPrice.setText(Float.toString(price));
        ratingBar.setRating(intent.getFloatExtra("rating", 2));
//        set Image banner
//        Glide.with(getApplicationContext()).load(getIntent().getStringExtra("image_resource")).into(imgBanner);
    }





}