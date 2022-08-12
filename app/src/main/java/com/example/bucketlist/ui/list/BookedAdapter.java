package com.example.bucketlist.ui.list;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bucketlist.R;
import com.example.bucketlist.models.Booking;
import com.example.bucketlist.activities.BookedActivity;

import java.util.List;

public class BookedAdapter extends RecyclerView.Adapter<BookedAdapter.BookingViewHolder> {

    //    Member variables
    private static List<Booking> mBookings; // Cached copy of events
    private  final Context mContext;

    public BookedAdapter(Context context) {
        this.mContext = context;
//        OnItemSelectedListener listener;
    }


    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BookingViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.booking_recyclerview_item, parent, false));
    }

    @Override
    public void onBindViewHolder(BookingViewHolder holder, int position) {

        Booking current = mBookings.get(position);
        // Populate the textViews with data.

        holder.bindTo(current);

        // Covers the case of data not being ready yet.

    }

   public void setEvents(List<Booking> booking) {
        mBookings = booking;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mEvents has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mBookings != null)
            return mBookings.size();
        else return 0;
    }

     class BookingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title, curator, bookingId;




        private BookingViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView_title);
            curator = itemView.findViewById(R.id.textView_curator);
            bookingId = itemView.findViewById(R.id.tvBookingId);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

//            listener.onDetailSelected();
            // TODO: 5/4/2022  implement open detail view

            Booking current = mBookings.get(getAdapterPosition());

            Intent detailIntent = new Intent(mContext, BookedActivity.class);
            detailIntent.putExtra("title", current.getTitle());
            detailIntent.putExtra("id", current.getId());
            detailIntent.putExtra("curator", current.getCurator());
            detailIntent.putExtra("dateTime", current.getDateTime());
            detailIntent.putExtra("venue", current.getVenue());

            mContext.startActivity(detailIntent);
        }

        void bindTo(Booking current) {
            // Populate the textViews with data.
            title.setText(current.getTitle());
            curator.setText(current.getCurator());
            bookingId.setText(current.getId());
        }


    }

}