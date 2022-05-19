package com.example.bucketlist.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.bucketlist.model.Event;
import com.example.bucketlist.util.Repository;

import java.util.List;

public class HomeFragViewModel extends AndroidViewModel {
    private Repository mRepository;
    private LiveData<List<Event>> mRecommendedEvents ;
    private LiveData<List<Event>> mFeaturedEvents;
    private LiveData<List<Event>> mTopEvents;
    private LiveData<List<Event>> mNearEvents;

    public HomeFragViewModel(@NonNull Application application) {
        super(application);
        mRepository = new Repository();
        mRecommendedEvents = mRepository.getRecommendedEvents();
        mFeaturedEvents = mRepository.getFeaturedEvents();
        mTopEvents = mRepository.getTopEvents();
        mNearEvents = mRepository.geNearEvents();
    }

    public LiveData<List<Event>> getRecommendedEvents() {
        if (mRecommendedEvents == null){
            mRecommendedEvents  = new MutableLiveData<>();
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

    public  LiveData<List<Event>> getNearEvents() {
        if (mNearEvents == null){
            mNearEvents  = new MutableLiveData<>();
        }
        return mNearEvents;
    }
}
