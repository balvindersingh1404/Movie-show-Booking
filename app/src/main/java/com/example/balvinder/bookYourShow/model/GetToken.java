package com.example.balvinder.bookYourShow.model;

public class GetToken{

    String deviceid;
    String brandname;
    String height;
    String manufacturer;
    String modelName;
    String notifyid;
    String osversion;
    String platform;
    String width;

    String splashText;

    public GetToken(String deviceid, String brandname, String height, String manufacturer, String modelName, String notifyid, String osversion, String platform, String width){

        this.deviceid=deviceid;
        this.brandname=brandname;
        this.height=height;
        this.manufacturer=manufacturer;
        this.modelName=modelName;
        this.notifyid=notifyid;
        this.osversion=osversion;
        this.platform=platform;
        this.width=width;

    }

    public GetToken() {

    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getBrandname() {
        return brandname;
    }

    public void setBrandname(String brandname) {
        this.brandname = brandname;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getNotifyid() {
        return notifyid;
    }

    public void setNotifyid(String notifyid) {
        this.notifyid = notifyid;
    }

    public String getOsversion() {
        return osversion;
    }

    public void setOsversion(String osversion) {
        this.osversion = osversion;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getSplashText() {
        return splashText;
    }

    public void setSplashText(String splashText) {
        this.splashText = splashText;
    }
}