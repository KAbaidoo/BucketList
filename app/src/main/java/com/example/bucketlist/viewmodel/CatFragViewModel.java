package com.example.bucketlist.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.bucketlist.model.Event;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.Locale;

public class CatFragViewModel extends AndroidViewModel {
//    private Repository mRepository;

    private MutableLiveData<List<Event>> mEvents ;
    private String category;
    FirebaseFirestore db;


    public CatFragViewModel(@NonNull Application application) {
        super(application);
        this.db = FirebaseFirestore.getInstance();
        mEvents = new MutableLiveData<>();
    }

    public LiveData<List<Event>> getEventsByCategory(String category) {

        db.collection("events")
                .whereEqualTo("category", category.toLowerCase(Locale.ROOT))
                .get().addOnSuccessListener(queryDocumentSnapshots -> {
            List<Event> events = queryDocumentSnapshots.toObjects(Event.class);
            mEvents.setValue(events);
        });

//        if (mEvents == null) {
//            mEvents = new MutableLiveData<>();
//        }
        return mEvents;
    }


}
