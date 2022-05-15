package com.example.bucketlist.model;

public class Event {

    private String id;
    private String title;
    private String info;
    private String date;
    private String venue;
    private String curator;
    private int imageResource;
    private int price;


    public Event() {
    }

    public Event(String id, String title, String info, String date, String venue, String curator, int imageResource, int price) {
        this.id = id;
        this.title = title;
        this.info = info;
        this.date = date;
        this.venue = venue;
        this.curator = curator;
        this.imageResource = imageResource;
        this.price = price;
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

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }



}
