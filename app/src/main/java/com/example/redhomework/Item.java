package com.example.redhomework;

public class Item {
    private int type = 0;
    private String time;

    public void setType(int type) {
        this.type = type;
    }

    private String author;
    private String title;


    public String getTime() {
        return time;
    }

    public int getType() {
        return type;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public Item(String time, String author, String title) {
        this.time = time;
        this.author = author;
        this.title = title;
    }

}
