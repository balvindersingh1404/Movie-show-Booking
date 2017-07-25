package com.example.balvinder.bookYourShow.model;

/**
 * Created by balvinder on 5/12/16.
 */

public class Album {
    private String name;
    private String detail;
    private int thumbnail;

    public Album() {
    }

    public Album(String name, String detail, int thumbnail) {
        this.name = name;
        this.detail = detail;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}