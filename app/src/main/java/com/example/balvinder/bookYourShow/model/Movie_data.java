package com.example.balvinder.bookYourShow.model;

public class Movie_data {

    private String movieId;
    private String masterMovieId;
    private String name;
    private String date_caption;
    private String like_count;

    public String getIs_liked() {
        return is_liked;
    }

    public void setIs_liked(String is_liked) {
        this.is_liked = is_liked;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMasterMovieId() {
        return masterMovieId;
    }

    public void setMasterMovieId(String masterMovieId) {
        this.masterMovieId = masterMovieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate_caption() {
        return date_caption;
    }

    public void setDate_caption(String date_caption) {
        this.date_caption = date_caption;
    }

    public String getLike_count() {
        return like_count;
    }

    public void setLike_count(String like_count) {
        this.like_count = like_count;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    private String is_liked;
    private String videoUrl;
    private String image;
    private String genre;
    private String caption;
    private String language;

}