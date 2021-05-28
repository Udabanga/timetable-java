package com.udayanga.timetableio.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Search {
    Batch batch;

    User user;

    Classroom classroom;

    @JsonFormat(pattern="EEE MMM dd HH:mm:ss zzzz yyyy")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date startDate;

    @JsonFormat(pattern="EEE MMM dd HH:mm:ss zzzz yyyy")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date endDate;

    public Search() {
    }

    public Search(Batch batch, User user, Classroom classroom, Date startDate, Date endDate) {
        this.batch = batch;
        this.user = user;
        this.classroom = classroom;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Search(Batch batch, User user, Date startDate, Date endDate) {
        this.batch = batch;
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Search(Classroom classroom, Date startDate, Date endDate) {
        this.classroom = classroom;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
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
