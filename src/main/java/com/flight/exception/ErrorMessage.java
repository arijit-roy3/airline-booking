package com.flight.exception;

import java.util.Date;

public class ErrorMessage {
    private Date date;
    private String meassage;
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getMeassage() {
        return meassage;
    }
    public void setMeassage(String meassage) {
        this.meassage = meassage;
    }

    public ErrorMessage()
    {

    }
    public ErrorMessage(Date date, String meassage) {
        super();
        this.date = date;
        this.meassage = meassage;
    }
}