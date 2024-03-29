package com.example.bucketlist.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bucketlist.R;
import com.example.bucketlist.activities.DetailActivity;
import com.example.bucketlist.models.Event;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RecommendedEventsAdapter extends RecyclerView.Adapter<RecommendedEventsAdapter.EventViewHolder> {

    //    Member variables
    private static List<Event> mEvents; // Cached copy of events
    private final Context mContext;


    public RecommendedEventsAdapter(Context context) {
        this.mContext = context;


    }


    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new EventViewHolder(LayoutInflater.from(mContext).inflate(R.layout.recyclerview_item, parent, false));

    }

    @Override
    public void onBindViewHolder(RecommendedEventsAdapter.EventViewHolder holder, int position) {

        Event current = mEvents.get(position);
        // Populate the textviews with data.
        holder.bindTo(current);

    }

    public void setEvents(List<Event> events) {
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

     class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView title;
        TextView curator;
        TextView price;
        ImageView bannerImg;
        TextView rating;
//        OnItemSelectedListener listener;

        private EventViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView_title);
            curator = itemView.findViewById(R.id.textView_curator);
            price = itemView.findViewById(R.id.textView_price);
            bannerImg = itemView.findViewById(R.id.imageView_banner);
            rating = itemView.findViewById(R.id.textView_rating);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

//            listener.onDetailSelected();
            // TODO: 5/4/2022  implement open detail view

            Event current = mEvents.get(getAdapterPosition());
//            Event current = mEvents.get(position);
            Intent detailIntent = new Intent(mContext, DetailActivity.class);
            detailIntent.putExtra("title", current.getTitle());
            detailIntent.putExtra("id", current.getId());
            detailIntent.putExtra("curator", current.getCurator());
            detailIntent.putExtra("price", current.getPrice());
            detailIntent.putExtra("dateTime", current.getDateTime());
            detailIntent.putExtra("rating", current.getRating());
            detailIntent.putExtra("image_resource",
                    current.getImageResource());
            detailIntent.putExtra("info", current.getInfo());


            detailIntent.putExtra("venue", current.getVenue());

            Calendar cal = Calendar.getInstance();
            Date dateTime = current.getDateTime();
            cal.setTime(dateTime);

            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            String str = day +
                    "-" +
                    (month + 1) +
                    "-" +
                    year;
//            Log.d("TopEvents", str.toString());
            detailIntent.putExtra("date", str);

//            Time
//            ================================================
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int min = cal.get(Calendar.MINUTE);


            String str1 = hour +
                    ":" +
                    min;

//            Log.d("TopEvents", str1.toString());
            detailIntent.putExtra("time", str1);


            mContext.startActivity(detailIntent);
        }

        void bindTo(Event current) {
            // Populate the textViews with data.
            title.setText(current.getTitle());
            curator.setText(current.getCurator());
            price.setText(Long.toString(current.getPrice()));
            rating.setText(Double.toString(current.getRating()));
            Glide.with(mContext).load(current.getImageResource()).placeholder(R.drawable.photo)
                    .fitCenter().into(bannerImg);
        }
    }
}