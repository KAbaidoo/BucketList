package com.example.bucketlist.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bucketlist.R;
import com.example.bucketlist.adapter.EventsAdapter;
import com.example.bucketlist.viewmodel.HomeFragViewModel;

import java.util.Calendar;
import java.util.Date;

public class HomeFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setGreeting(view);


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
//        RecyclerView mRecommendedRecyclerView = view.findViewById(R.id.recyclerView_recommended);
//        RecyclerView mFeaturedRecyclerView = view.findViewById(R.id.recyclerView_featured);
//        RecyclerView mTopRecyclerView = view.findViewById(R.id.recyclerView_top);
//        RecyclerView mNearRecyclerView = view.findViewById(R.id.recyclerView_near);


//        EventsAdapter mRAdapter = new EventsAdapter(getContext());
//        EventsAdapter mFAdapter = new EventsAdapter(getContext());
//        EventsAdapter mTAdapter = new EventsAdapter(getContext());
//        EventsAdapter mNAdapter = new EventsAdapter(getContext());

//        mRecommendedRecyclerView.setAdapter(mRAdapter);
//        mFeaturedRecyclerView.setAdapter(mFAdapter);
//        mTopRecyclerView.setAdapter(mTAdapter);
//        mNearRecyclerView.setAdapter(mTAdapter);

//        mRecommendedRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
//        mFeaturedRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
//        mTopRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
//        mNearRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));


        Context context = getContext();
        HomeFragViewModel mHomeFragViewModel = new ViewModelProvider(this).get(HomeFragViewModel.class);
        mHomeFragViewModel.getRecommendedEvents().observe(this, events -> createRecyclerView(view, R.id.recyclerView_recommended, context).setEvents(events));
        mHomeFragViewModel.getFeaturedEvents().observe(this, events -> createRecyclerView(view, R.id.recyclerView_top, context).setEvents(events));
        mHomeFragViewModel.getTopEvents().observe(this, events -> createRecyclerView(view, R.id.recyclerView_featured, context).setEvents(events));
        mHomeFragViewModel.getNearEvents().observe(this, events ->createRecyclerView(view, R.id.recyclerView_near, context).setEvents(events));
    }

    private EventsAdapter createRecyclerView(View view, int recyclerView, Context context) {
        RecyclerView mRecyclerView = view.findViewById(recyclerView);
        EventsAdapter mAdapter = new EventsAdapter(context);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        return mAdapter;
    }

    private void setGreeting(View v) {
        TextView mGreeting = v.findViewById(R.id.textView_greeting);
        String greeting;
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);


        if (hour >= 5 && hour <= 12) {
            greeting = "Good morning";
        } else if (hour > 12 && hour < 18) {
            greeting = "Good afternoon";
        } else {
            greeting = "Good evening";
        }

        mGreeting.setText(greeting);

    }


//    private void openDetail() {
//
//        getActivity().getSupportFragmentManager().beginTransaction()
//                .replace(R.id.fragment_container, new DetailFragment())
//                .addToBackStack(null)
//                .commit();
//
//    }

//    private OnItemSelectedListener listener;
//
//    // Store the listener (activity) that will have events fired once the fragment is attached
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnItemSelectedListener) {
//            listener = (OnItemSelectedListener) context;
//        } else {
//            throw new ClassCastException(context.toString()
//                    + " must implement .OnItemSelectedListener");
//        }
//    }


}