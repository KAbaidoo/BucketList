package com.example.bucketlist.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bucketlist.model.Booking;
import com.example.bucketlist.model.Event;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class ListViewModel extends ViewModel {
    private final MutableLiveData<List<Event>> bucketList = new MutableLiveData<>();
    private final MutableLiveData<List<Booking>> bookings = new MutableLiveData<>();

    CollectionReference userListRef, sales;
    private FirebaseFirestore db;
    FirebaseAuth auth;
    String uid;


    public ListViewModel() {
        this.db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        uid = user.getUid();
        userListRef = db.collection("users").document(uid).collection("list");
        sales = db.collection("sales");
    }

    public LiveData<List<Event>> getBucketList() {
        loadBucketList();
        return bucketList;
    }

    public LiveData<List<Booking>> getBookings() {
        loadBookings();
        return bookings;
    }

    private void loadBookings() {


        sales
                .whereEqualTo("uid", uid)
                .get()
                .addOnCompleteListener(task -> {
                    List<Booking> list = new ArrayList<>();
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {

                            DocumentReference docRef = db.collection("events").document(document.get("eventId").toString());
                           docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                               @Override
                               public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                   DocumentSnapshot doc = task.getResult();
                                   if (doc.exists()){

                                       Event event = doc.toObject(Event.class);
                                       list.add(new Booking("id: "+document.getId(),event.getTitle(),event.getVenue(), event.getDateTime(),event.getCurator()));
                                       bookings.setValue(list);
                                       Log.d("ListView", String.valueOf(list));
                                   }
                               }
                           });
                        }


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
