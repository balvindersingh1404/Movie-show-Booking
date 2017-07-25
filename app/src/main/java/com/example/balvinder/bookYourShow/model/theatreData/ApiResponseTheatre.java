package com.example.balvinder.bookYourShow.model.theatreData;

/**
 * Created by balvinder on 8/12/16.
 */


public class ApiResponseTheatre {
    private Integer code;
    private String status;
    private String message;
    private AllTheatresResponseModel data;
    private String version;
    private String minversion;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AllTheatresResponseModel getData() {
        return data;
    }

    public void setData(AllTheatresResponseModel data) {
        this.data = data;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMinversion() {
        return minversion;
    }

    public void setMinversion(String minversion) {
        this.minversion = minversion;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
