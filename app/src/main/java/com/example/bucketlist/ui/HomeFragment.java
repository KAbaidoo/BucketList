package com.example.bucketlist.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bucketlist.R;
import com.example.bucketlist.adapter.RecommendedEventsAdapter;
import com.example.bucketlist.adapter.TopEventsAdapter;
import com.example.bucketlist.viewmodel.MainViewModel;

import java.util.Calendar;
import java.util.Date;

public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


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



//        Set up recyclerView
        RecyclerView mRecommendedRecyclerView = view.findViewById(R.id.recyclerView_recommended);
//        RecyclerView mFeaturedRecyclerView = view.findViewById(R.id.recyclerView_featured);
        RecyclerView mTopRecyclerView = view.findViewById(R.id.recyclerView_top);
//        RecyclerView mNewRecyclerView = view.findViewById(R.id.recyclerView_new);


        RecommendedEventsAdapter mRAdapter = new RecommendedEventsAdapter(getContext());
        TopEventsAdapter mTAdapter = new TopEventsAdapter(getContext());



        mRecommendedRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mTopRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mRecommendedRecyclerView.setAdapter(mRAdapter);
        mTopRecyclerView.setAdapter(mTAdapter);


        FragmentActivity fragmentActivity = requireActivity();
        LifecycleOwner lifecycleOwner = getViewLifecycleOwner();

       MainViewModel viewModel = new ViewModelProvider(fragmentActivity).get(MainViewModel.class);
        viewModel.getTopEvents().observe(lifecycleOwner, events -> mTAdapter.setEvents(events));

//        RecommendViewModel recommendViewModel = new ViewModelProvider(fragmentActivity).get(RecommendViewModel.class);
        viewModel.getRecommendedEvents().observe(lifecycleOwner, events -> mRAdapter.setEvents(events));


//        mTopRecyclerView.setAdapter(mTAdapter);
//        mNewRecyclerView.setAdapter(mNAdapter);
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




}