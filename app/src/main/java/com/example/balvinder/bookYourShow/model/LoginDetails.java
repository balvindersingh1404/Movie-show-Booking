package com.example.balvinder.bookYourShow.model;

/**
 * Created by balvinder on 17/11/16.
 */

public class LoginDetails {
    String email;
    String mobile_no;
    String password;
    String user_name;
    String socialType;
    String userId;
    String accessToken;
    String profile_image;
    String otp;

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getSocialType() {
        return socialType;
    }

    public void setSocialType(String socialType) {
        this.socialType = socialType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    //For LOGIN
    public LoginDetails(String email, String mobile_no, String password) {
        this.email = email;
        this.mobile_no = mobile_no;
        this.password = password;
    }
    // For REGISTRATION
    public LoginDetails(String user_name,String email, String mobile_no, String password) {
        this.user_name=user_name;
        this.email = email;
        this.mobile_no = mobile_no;
        this.password = password;
    }

   // For SOCIAL LOGIN
    public LoginDetails(String socialType,String userId,String accessToken,String email,String user_name, String mobile_no, String profile_image) {
       this.socialType=socialType;
        this.userId=userId;
        this.accessToken=accessToken;
        this.email = email;
        this.user_name=user_name;
        this.mobile_no = mobile_no;
        this.profile_image = profile_image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginDetails(){

    }

}
