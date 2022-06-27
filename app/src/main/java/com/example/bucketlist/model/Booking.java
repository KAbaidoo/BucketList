package com.example.bucketlist.model;

import com.google.firebase.firestore.DocumentId;

import java.util.Date;

public class Booking {
    @DocumentId
    private String id;
    private String title;
    private String venue;
    private Date dateTime;
    private String curator;

    public Booking(String id, String title, String venue, Date dateTime, String curator) {
        this.id = id;
        this.title = title;
        this.venue = venue;
        this.dateTime = dateTime;
        this.curator = curator;
    }

    public Booking() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getCurator() {
        return curator;
    }

    public void setCurator(String curator) {
        this.curator = curator;
    }

//    TextView mGreeting = v.findViewById(R.id.textView_greeting);
//    String greeting;
//    Date date = new Date();
//    Calendar cal = Calendar.getInstance();
//        cal.setTime(date);
//    int hour = cal.get(Calendar.HOUR_OF_DAY);
//
}
