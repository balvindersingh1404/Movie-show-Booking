package com.example.balvinder.bookYourShow.model;

public class ContactDetails {
String contactOption,phoneNumber,emailId,comments;

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setContactOption(String contactOption) {
        this.contactOption = contactOption;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getComments() {
        return comments;
    }

    public String getContactOption() {
        return contactOption;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}