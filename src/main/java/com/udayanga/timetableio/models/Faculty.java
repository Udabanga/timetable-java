package com.udayanga.timetableio.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "faculties")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "facultyName")
    private String facultyName;

    public Faculty() {
    }

    public Faculty(String facultyName) {
        this.facultyName = facultyName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", facultyName='" + facultyName + '\'' +
                '}';
    }
}
