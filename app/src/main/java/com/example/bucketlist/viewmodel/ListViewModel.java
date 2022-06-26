package com.example.bucketlist.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bucketlist.model.Event;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class ListViewModel extends ViewModel {
    private final MutableLiveData<List<Event>> bucketList = new MutableLiveData<>();
    private final MutableLiveData<List<Event>> bookings = new MutableLiveData<>();

    CollectionReference userListRef;
    private FirebaseFirestore db;
    FirebaseAuth auth;

    public ListViewModel() {
        this.db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        String uid = user.getUid();
        userListRef = db.collection("users").document(uid).collection("list");
    }

    public LiveData<List<Event>> getBucketList() {
        loadBucketList();
        return bucketList;
    }

    public LiveData<List<Event>> getBookings() {
        loadBookings();
        return bookings;
    }

    private void loadBookings() {
        List<Event> list = new ArrayList<>();
        userListRef
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            list.add(document.toObject(Event.class));
                        }
                        bookings.setValue(list);
                    }
                });
    }


    private void loadBucketList() {
        List<Event> list = new ArrayList<>();
        userListRef.get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            list.add(document.toObject(Event.class));
                        }
                        bucketList.setValue(list);
                    }
                });
    }



}
