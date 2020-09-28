package com.example.newsample_design;

public class Model {
    private int id;
    private String price_per_tiffin;
    private String price_per_month;
    private String price_per_year;
    private String price_per_week;
    private byte[] image;

    public Model(int id, String price_per_tiffin, String price_per_month, String price_per_year, String price_per_week, byte[] image) {
        this.id = id;
        this.price_per_tiffin = price_per_tiffin;
        this.price_per_month = price_per_month;
        this.price_per_year = price_per_year;
        this.price_per_week = price_per_week;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrice_per_tiffin() {
        return price_per_tiffin;
    }

    public void setPrice_per_tiffin(String price_per_tiffin) {
        this.price_per_tiffin = price_per_tiffin;
    }

    public String getPrice_per_month() {
        return price_per_month;
    }

    public void setPrice_per_month(String price_per_month) {
        this.price_per_month = price_per_month;
    }

    public String getPrice_per_year() {
        return price_per_year;
    }

    public void setPrice_per_year(String price_per_year) {
        this.price_per_year = price_per_year;
    }

    public String getPrice_per_week() {
        return price_per_week;
    }

    public void setPrice_per_week(String price_per_week) {
        this.price_per_week = price_per_week;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}