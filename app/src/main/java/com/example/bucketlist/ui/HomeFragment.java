package com.example.bucketlist.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bucketlist.R;
import com.example.bucketlist.adapter.EventsAdapter;
import com.example.bucketlist.model.Event;
import com.example.bucketlist.util.OnItemSelectedListener;
import com.example.bucketlist.viewmodel.HomeFragViewModel;

import java.util.List;

public class HomeFragment extends Fragment {
    EventsAdapter mAdapter;
    HomeFragViewModel mHomeFragViewModel;
   List<Event> mRecommendedEvents;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


//        Button mSignOutButton = view.findViewById(R.id.button_signOut);
//        mSignOutButton.setOnClickListener(v -> listener.onSignOutSelected()
//        );
////grab the detail button
//        Button mDetailsButton = view.findViewById(R.id.button_detail);
//        mDetailsButton.setOnClickListener(v ->
//
//                        listener.onDetailSelected()
////                        openDetail()
//        );


//        Set up recyclerView
        RecyclerView mRecommendedRecyclerView = view.findViewById(R.id.recyclerView_recommended);
        RecyclerView mFeaturedRecyclerView = view.findViewById(R.id.recyclerView_featured);
        RecyclerView mTopRecyclerView = view.findViewById(R.id.recyclerView_top);

        mAdapter = new EventsAdapter( getContext(),mRecommendedEvents );
        mRecommendedRecyclerView.setAdapter(mAdapter);
        mFeaturedRecyclerView.setAdapter(mAdapter);
        mTopRecyclerView.setAdapter(mAdapter);

        mRecommendedRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        mFeaturedRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        mTopRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));


        mHomeFragViewModel = new ViewModelProvider(this).get(HomeFragViewModel.class);
        final Observer<List<Event>> eventObserver = events -> mAdapter.setEvents(events);

        mHomeFragViewModel.getRecommendedEvents().observe(this, eventObserver);
        mHomeFragViewModel.getFeaturedEvents().observe(this, eventObserver);
        mHomeFragViewModel.getTopEvents().observe(this, eventObserver);
    }

    private void openDetail() {

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new DetailFragment())
                .addToBackStack(null)
                .commit();

    }

    private OnItemSelectedListener listener;

    // Store the listener (activity) that will have events fired once the fragment is attached
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemSelectedListener) {
            listener = (OnItemSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement .OnItemSelectedListener");
        }
    }


}