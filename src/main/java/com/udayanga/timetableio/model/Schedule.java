package com.udayanga.timetableio.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "schedules")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private  String stringDate, stringStartTime, stringEndTime;


    public String getStringDate() {
        return stringDate;
    }

    public void setStringDate(String stringDate) {
        this.stringDate = stringDate;
    }

    public String getStringStartTime() {
        return stringStartTime;
    }

    public void setStringStartTime(String stringStartTime) {
        this.stringStartTime = stringStartTime;
    }

    public String getStringEndTime() {
        return stringEndTime;
    }

    public void setStringEndTime(String stringEndTime) {
        this.stringEndTime = stringEndTime;
    }

    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date date;

    @Column(name = "startTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date startTime;

    @Column(name = "endTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date endTime;

//    yyyy-MM-dd'T'HH:mm
    @ManyToOne
    @JoinColumn(name="classroom_id")
    private Classroom classroom;

    @ManyToOne
    @JoinColumn(name="course_id")
    private Module module;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "schedule_batch",
            joinColumns = @JoinColumn(name = "schdeule_id"),
            inverseJoinColumns = @JoinColumn(name = "batch_id"))
    private Set<Batch> batches = new HashSet<>();

    public Schedule() {
    }

    public Schedule(Date date, Date startTime, Date endTime, Classroom classroom, Module module, User user, Set<Batch> batches) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.classroom = classroom;
        this.module = module;
        this.user = user;
        this.batches = batches;
    }

    public Schedule(long id, Date date, Date startTime, Date endTime, Classroom classroom, Module module, User user, Set<Batch> batches) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.classroom = classroom;
        this.module = module;
        this.user = user;
        this.batches = batches;
    }

    public Schedule(Date date, Date endTime, Date startTime,String stringDate,String stringEndTime, String stringStartTime, Classroom classroom, Module module, User user, Set<Batch> batches){
        this.date = date;
        this.endTime = endTime;
        this.startTime = startTime;
        this.stringDate = stringDate;
        this.stringEndTime = stringEndTime;
        this.stringStartTime = stringStartTime;
        this.classroom = classroom;
        this.module = module;
        this.user = user;
        this.batches = batches;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Batch> getBatches() {
        return batches;
    }

    public void setBatches(Set<Batch> batches) {
        this.batches = batches;
    }
}