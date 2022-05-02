package com.example.bucketlist.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bucketlist.R;
import com.example.bucketlist.data.Event;

import java.util.List;


public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventViewHolder> {

    static class EventViewHolder extends RecyclerView.ViewHolder {
        private final TextView taskItemView;

        private EventViewHolder(View itemView) {
            super(itemView);
            taskItemView = itemView.findViewById(R.id.title);
        }

    }
    private final LayoutInflater mInflater;
    private List<Event> mEvents; // Cached copy of tasks

    EventsAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new EventViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(EventsAdapter.EventViewHolder holder, int position) {
        if (mEvents != null) {
            Event current = mEvents.get(position);
            holder.taskItemView.setText(current.getEvent());
        } else {
            // Covers the case of data not being ready yet.
            holder.taskItemView.setText("No Event");
        }
    }

    void setEvents(List<Event> events){
        mEvents = events;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mEvents has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mEvents != null)
            return mEvents.size();
        else return 0;
    }

    public Event getEventAtPosition (int position){
        return mEvents.get(position);
    }


}