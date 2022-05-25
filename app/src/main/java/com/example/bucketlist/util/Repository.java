package com.example.bucketlist.util;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.bucketlist.model.Event;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class Repository {
    FirebaseFirestore db;
    private MutableLiveData<List<Event>> mRecommendedEvents;
    private MutableLiveData<List<Event>> mFeaturedEvents;
    private MutableLiveData<List<Event>> mTopEvents;
    private MutableLiveData<List<Event>> mNearEvents;


    public Repository() {
        this.mRecommendedEvents = new MutableLiveData<>();
        this.mFeaturedEvents = new MutableLiveData<>();
        this.mTopEvents = new MutableLiveData<>();
        this.mNearEvents = new MutableLiveData<>();
        this.db = FirebaseFirestore.getInstance();
        loadEvents();
    }

    void loadEvents() {
        db.collection("events").get().addOnSuccessListener(queryDocumentSnapshots -> {
            List<Event> events = queryDocumentSnapshots.toObjects(Event.class);
            mRecommendedEvents.setValue(events);
            mFeaturedEvents.setValue(events);
            mTopEvents.setValue(events);
            mNearEvents.setValue(events);
        });

    }


    public LiveData<List<Event>> getRecommendedEvents() {
        if (mRecommendedEvents == null) {
            mRecommendedEvents = new MutableLiveData<>();
        }
        return mRecommendedEvents;
    }

    public LiveData<List<Event>> getFeaturedEvents() {
        if (mFeaturedEvents == null) {
            mFeaturedEvents = new MutableLiveData<>();
        }
        return mFeaturedEvents;
    }

    public LiveData<List<Event>> getTopEvents() {

        if (mTopEvents == null) {
            mTopEvents = new MutableLiveData<>();
        }
        return mTopEvents;
    }

    public LiveData<List<Event>> getNearEvents() {
        if (mNearEvents == null) {
            mNearEvents = new MutableLiveData<>();
        }
        return mNearEvents;
    }


}
