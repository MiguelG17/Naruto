package com.example.naruto.Model;

public class Cards {

    private String number;
    private String username;
    private String imageurl;
    private String category;

    public Cards(String number, String username, String imageurl, String category) {
        this.number = number;
        this.username = username;
        this.imageurl = imageurl;
        this.category = category;
    }

    public Cards() {
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUsername() {
        return username;
    }

    public void setUserame(String name) {
        this.username = username;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
