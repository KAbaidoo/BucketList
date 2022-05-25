package com.example.bucketlist.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bucketlist.model.Event;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainViewModel extends ViewModel {
    private final MutableLiveData<List<Event>> topEvents = new MutableLiveData<>();
    private final MutableLiveData<List<Event>> recEvents = new MutableLiveData<>();
    private final MutableLiveData<List<Event>> searchEvents = new MutableLiveData<>();

    private FirebaseFirestore db;
    CollectionReference eventsRef;

    public MainViewModel() {
        this.db = FirebaseFirestore.getInstance();
        eventsRef = db.collection("events");
        loadTopEvents();
        loadRecommendedEvents();
    }


    public LiveData<List<Event>> getTopEvents() {
//       loadTopEvents();
        return topEvents;
    }
    public LiveData<List<Event>> getRecommendedEvents() {
//        loadRecommendedEvents();
        return recEvents;
    }



    private void loadTopEvents() {
        List<Event> list = new ArrayList<>();
        eventsRef.whereGreaterThan("rating", 4.0)
                .orderBy("rating", Query.Direction.DESCENDING)
                .limit(6)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        for (QueryDocumentSnapshot document : task.getResult()){
                            list.add(document.toObject(Event.class));
                        }
                        topEvents.setValue(list);
                    }
                });
    }

    private void loadRecommendedEvents() {
        List<Event> list = new ArrayList<>();
        eventsRef.get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            list.add(document.toObject(Event.class));
                        }
                        recEvents.setValue(list);
                    }

                });
    }

    public LiveData<List<Event>> getEventsByCategory(String category) {
        eventsRef
                .whereEqualTo("category", category.toLowerCase(Locale.ROOT))
                .get().addOnSuccessListener(queryDocumentSnapshots -> {
            List<Event> events = queryDocumentSnapshots.toObjects(Event.class);
            searchEvents.setValue(events);
        });
        return searchEvents;
    }





}
