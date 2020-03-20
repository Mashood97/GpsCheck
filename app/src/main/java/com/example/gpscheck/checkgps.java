package com.example.gpscheck;

public class checkgps {
    String offid,title,datetime;


    public checkgps() {
    }

    public checkgps(String offid, String title, String datetime) {
        this.offid = offid;
        this.title = title;
        this.datetime = datetime;
    }

    public checkgps(String title, String datetime) {
        this.title = title;
        this.datetime = datetime;
    }

    public String getOffid() {
        return offid;
    }

    public void setOffid(String offid) {
        this.offid = offid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
