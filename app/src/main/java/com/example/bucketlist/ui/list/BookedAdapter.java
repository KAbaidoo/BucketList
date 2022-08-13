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
import com.example.bucketlist.activities.BookedActivity;
import com.example.bucketlist.models.Booking;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BookedAdapter extends RecyclerView.Adapter<BookedAdapter.BookingViewHolder> {

    //    Member variables
    private static List<Booking> mBookings; // Cached copy of events
    private  final Context mContext;

    public BookedAdapter(Context context) {
        this.mContext = context;

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
        TextView title, curator, bookingId, time, date;




        private BookingViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView_title);
            curator = itemView.findViewById(R.id.textView_curator);
            bookingId = itemView.findViewById(R.id.tvBookingId);
            date = itemView.findViewById(R.id.tv_date);
            time = itemView.findViewById(R.id.tv_time);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Booking current = mBookings.get(getAdapterPosition());
            Intent detailIntent = new Intent(mContext, BookedActivity.class);
            detailIntent.putExtra("title", current.getTitle());
            detailIntent.putExtra("id", current.getId());
            detailIntent.putExtra("curator", current.getCurator());
            detailIntent.putExtra("venue", current.getVenue());
            mContext.startActivity(detailIntent);
        }

        void bindTo(Booking current) {
            // Populate the textViews with data.
            title.setText(current.getTitle());
            curator.setText(current.getCurator());
            bookingId.setText(current.getId());

            Calendar cal = Calendar.getInstance();
            Date dateTime = current.getDateTime();
            cal.setTime(dateTime);

//            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            String dayString = Integer.toString(day);
            String monthString = Integer.toString(month+1);

            if(dayString.length() == 1){
                dayString= "0"+dayString;
            }
            if(monthString.length() == 1){
                monthString= "0"+monthString;
            }
            String str = dayString +
                    "/" +
                    (monthString);
            date.setText(str);


//            Time
//            ================================================
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int min = cal.get(Calendar.MINUTE);
            String minString = Integer.toString(min);

            if(minString.length() == 1){
              minString= "0"+minString;
            }
            String str1 = hour +
                    ":" +
                    minString;
            time.setText(str1);
        }


    }

}