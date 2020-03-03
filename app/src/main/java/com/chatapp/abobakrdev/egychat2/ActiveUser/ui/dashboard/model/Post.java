package com.chatapp.abobakrdev.egychat2.ActiveUser.ui.dashboard.model;

public class Post {
    private String text;
    private String color ;
    private String mail;
    private String date;
    private String day;

    public Post() {
    }

    public Post(String text, String color, String mail, String date, String day) {
        this.text = text;
        this.color = color;
        this.mail = mail;
        this.date = date;
        this.day = day;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
