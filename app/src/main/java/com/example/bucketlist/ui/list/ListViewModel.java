package com.example.bucketlist.ui.list;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bucketlist.models.Booking;
import com.example.bucketlist.models.Event;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListViewModel extends ViewModel {
    private final MutableLiveData<List<Event>> bucketList = new MutableLiveData<>();
    private final MutableLiveData<List<Booking>> bookings = new MutableLiveData<>();

    CollectionReference userListRef, sales;
    private final FirebaseFirestore db;
    FirebaseAuth auth;
    String uid;


    public ListViewModel() {
        this.db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        assert user != null;
        uid = user.getUid();
        userListRef = db.collection("users").document(uid).collection("list");
        sales = db.collection("sales");
    }

    public LiveData<List<Event>> getBucketList() {
        loadBucketList();
        return bucketList;
    }

    public void removeEvent(int position) {
        assert position != -1;
    Event e = Objects.requireNonNull(bucketList.getValue()).get(position);
        userListRef.document(e.getId())
                .delete()
                .addOnSuccessListener(aVoid -> Log.d("ListFragment", e.getTitle() + " Successfully deleted!"))
                .addOnFailureListener(e1 -> Log.w("ListFragment", "Error deleting document", e1));
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

                            DocumentReference docRef = db.collection("events").document(Objects.requireNonNull(document.get("eventId")).toString());
                           docRef.get().addOnCompleteListener(task1 -> {
                               DocumentSnapshot doc = task1.getResult();
                               if (doc.exists()){
                                   Event event = doc.toObject(Event.class);
                                   assert event != null;
                                   list.add(new Booking("id: "+document.getId(),event.getTitle(),event.getVenue(),event.getDateTime(),event.getCurator()));
                                   bookings.setValue(list);
                                   Log.d("ListView", String.valueOf(list));
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
