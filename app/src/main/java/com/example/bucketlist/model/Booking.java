package com.example.bucketlist.model;

import com.google.firebase.firestore.DocumentId;

public class Booking {
    @DocumentId
    private String id;
    private String title;
    private String info;
    private String date;
    private String venue;
    private String time;
    private String curator;

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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getCurator() {
        return curator;
    }

    public void setCurator(String curator) {
        this.curator = curator;
    }









    public String getRating() {
        return time;
    }

    public void setRating(String rating) {
        this.time = rating;
    }
}
