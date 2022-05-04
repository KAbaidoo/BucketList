package com.example.bucketlist.util;

import androidx.lifecycle.LiveData;

import com.example.bucketlist.model.Event;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class Repository {
    private final FirebaseFirestore FIRESTORE;
    private LiveData<List<Event>> mRecommendedEvents;
    private LiveData<List<Event>> mFeaturedEvents;

    public Repository() {
        this.FIRESTORE = FirebaseUtil.getFirestore();
    }

    public LiveData<List<Event>> getRecommendedEvents() {
        return mRecommendedEvents;
    }

    public LiveData<List<Event>> getFeaturedEvents() {
        return mFeaturedEvents;
    }
}
