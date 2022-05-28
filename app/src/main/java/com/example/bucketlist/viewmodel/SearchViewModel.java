package com.example.bucketlist.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bucketlist.model.Event;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.Locale;

public class SearchViewModel extends ViewModel {

    private final MutableLiveData<List<Event>> searchEvents = new MutableLiveData<>();

    private FirebaseFirestore db;
    CollectionReference eventsRef;

    public SearchViewModel() {
        this.db = FirebaseFirestore.getInstance();
        eventsRef = db.collection("events");
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
