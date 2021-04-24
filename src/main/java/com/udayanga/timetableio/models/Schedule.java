package com.udayanga.timetableio.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "schedules")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "startTime")
    private String startTime;

    @Column(name = "endTime")
    private String endTime;

    @ManyToOne
    @JoinColumn(name="classroom_id")
    private Classroom classroom;

    @ManyToOne
    @JoinColumn(name="course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "schedule_batch",
            joinColumns = @JoinColumn(name = "schdeule_id"),
            inverseJoinColumns = @JoinColumn(name = "batch_id"))
    private Set<Batch> roles = new HashSet<>();

    public Schedule() {

    }

    public Schedule(String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public long getId() {
        return id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }


    @Override
    public String toString() {
        return "Schedules [id=" + id + ", startTime=" + startTime + ", endTime=" + endTime + "]";
    }
}