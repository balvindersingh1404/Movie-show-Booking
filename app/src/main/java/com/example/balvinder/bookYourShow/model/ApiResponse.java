package com.example.balvinder.bookYourShow.model;

import java.util.List;

public class ApiResponse {

    private int code;
    private String status;
    private Data data;
    private String message;
    private List<List<Datum>> mfydata;


    public List<List<Datum>> getMfydata() {
        return mfydata;
    }

    public void setMfydata(List<List<Datum>> mfydata) {
        this.mfydata = mfydata;
    }

    /**
     *
     * @return
     * The code
     */
    public int getCode() {
        return code;
    }

    /**
     *
     * @param code
     * The code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     *
     * @return
     * The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The data
     */
    public Data getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(Data data) {
        this.data = data;
    }

    /**
     *
     * @return
     * The message
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     * The message
     */
    public void setMessage(String message) {
        this.message = message;
    }



}