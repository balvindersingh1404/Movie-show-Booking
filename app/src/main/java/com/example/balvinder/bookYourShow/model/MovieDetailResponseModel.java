package com.example.balvinder.bookYourShow.model;

/**
 * Created by balvinder on 13/12/16.
 */

public class MovieDetailResponseModel {

    private Integer code;
    private String status;
    private String message;
    private MovieDetail data;
    private String version;

    public MovieDetail getMovieDetail() {
        return data;
    }

    public void setMovieDetail(MovieDetail data) {
        this.data = data;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }


}
