package com.example.balvinder.bookYourShow.model;

import java.util.ArrayList;
import java.util.List;


public class OfferResponseModel {
    private List<Offer_data> data = new ArrayList<Offer_data>();
    private Integer code;
    private String status;
    private String message;


    public Integer getCode() {
        return code;
    }

    public List<Offer_data> getData() {
        return data;
    }

    public void setData(List<Offer_data> data) {
        this.data = data;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

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

}