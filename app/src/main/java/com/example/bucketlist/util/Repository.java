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


    public Repository() {
        this.mRecommendedEvents = new MutableLiveData<>();
        this.db = FirebaseUtil.getFirestore();
        loadEvents();
    }

    void loadEvents() {
        db.collection("events").get().addOnSuccessListener(queryDocumentSnapshots -> {
            List<Event> events = queryDocumentSnapshots.toObjects(Event.class);
            mRecommendedEvents.setValue(events);
            mFeaturedEvents.setValue(events);
            mTopEvents.setValue(events);
        });

    }

//    public void loadEvents(FirebaseFirestore db){
//        new loadEventsAsyncTask(db).execute();
//    }
//
//    private  class loadEventsAsyncTask extends AsyncTask<Void, Void, Void> {
//        private final FirebaseFirestore db;
//
//        loadEventsAsyncTask(FirebaseFirestore db) {
//            this.db = db;
//        }
//        @Override
//        protected Void doInBackground(Void... voids) {
//            db.collection("events").get().addOnSuccessListener(queryDocumentSnapshots -> {
//                List<Event> events = queryDocumentSnapshots.toObjects(Event.class);
//                mRecommendedEvents.setValue(events);
//                mFeaturedEvents.setValue(events);
//                mTopEvents.setValue(events);
//            });
//            return null;
//        }
//    }



    public LiveData<List<Event>> getRecommendedEvents() {
        if (mRecommendedEvents == null) {
            mRecommendedEvents = new MutableLiveData<>();
        }
        return mRecommendedEvents;
    }

    public LiveData<List<Event>> getFeaturedEvents() {
        if (mFeaturedEvents == null){
            mFeaturedEvents  = new MutableLiveData<>();
        }
        return mFeaturedEvents;
    }

    public LiveData<List<Event>> getTopEvents() {
        if (mTopEvents == null){
            mTopEvents  = new MutableLiveData<>();
        }
        return mTopEvents;
    }
}
