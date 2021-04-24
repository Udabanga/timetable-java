package com.udayanga.timetableio.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "courseName")
    private String courseName;

    @OneToMany(mappedBy = "course")
    private List<Schedule> schedules;


    public Course() {
    }

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                '}';
    }
}
