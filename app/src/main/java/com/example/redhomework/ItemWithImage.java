package com.example.redhomework;

public class ItemWithImage extends Item {
    private String time;
    private int type = 1;

    public ItemWithImage(String time, String author, String title) {
        super(time,author,title);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ItemWithImage(String time, String author, String title, String image) {
        super(time, author, title);
        this.time=time;
        this.author=author;
        this.image=image;
        this.title=title;
    }

    public String getTime() {
        return time;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    private String author;
    private String title;
    private String image;


}
