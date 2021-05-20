package com.udayanga.timetableio.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Search {
    Batch batch;

    User user;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date endDate;

    public Search() {
    }

    public Search(Batch batch, User user, Date startDate, Date endDate) {
        this.batch = batch;
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
