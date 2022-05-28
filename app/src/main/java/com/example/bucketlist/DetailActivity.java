package com.example.bucketlist;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class DetailActivity extends AppCompatActivity {
    FirebaseFirestore db;
    CollectionReference userListRef;
    CollectionReference eventsRef;
    FirebaseAuth auth;
    Map doc;
    public static final String TAG = ".DetailActivity.TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        String uid = user.getUid();

        userListRef = db.collection("users").document(uid).collection("list");
        eventsRef = db.collection("events");

        Intent intent = getIntent();
        String eventId = intent.getStringExtra("id");
        loadEvent(eventId);

//        Grab textViews
        TextView eventTitle = (TextView) findViewById(R.id.textView_detail_title);
        TextView eventPrice = (TextView) findViewById(R.id.detail_price);
        TextView description = (TextView) findViewById(R.id.textView_description);
        ImageView imgBanner = (ImageView) findViewById(R.id.imageView_detail);
        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar_detail);

//        Grab buttons
        Button addToList = (Button) findViewById(R.id.button_add);
        Button bookEvent = (Button) findViewById(R.id.button_book);

//        Set TextViews
        eventTitle.setText(intent.getStringExtra("title"));
        description.setText(intent.getStringExtra("info"));
        eventPrice.setText(Float.toString(intent.getFloatExtra("price", 0)));
        ratingBar.setRating(intent.getFloatExtra("rating", 2));
//        set Image banner
        Glide.with(getApplicationContext()).load(getIntent().getStringExtra("image_resource")).into(imgBanner);


        addToList.setOnClickListener(v -> userListRef.document(eventId).set(doc)
                .addOnSuccessListener(unused -> Log.d(TAG, "Document added successfully!"))
                .addOnFailureListener(e -> Log.w(TAG, "Error writing document", e)));

    }

    private void loadEvent(String eventId) {
        eventsRef.document(eventId).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            doc = document.getData();

//                                        Log.d("DetailActivity Get", "DocumentSnapshot data: " + document.getData());
                        } else {
                            Log.d(TAG, "No such document");
                        }
                    } else {
                        Log.d(TAG, "get failed with", task.getException());
                    }
                });
    }


}