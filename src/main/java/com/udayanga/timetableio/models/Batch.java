package com.udayanga.timetableio.models;


import javax.persistence.*;

@Entity
@Table(name = "batches")
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "faculty")
    private String faculty;

    @Column(name = "year")
    private int year;

    @Column(name = "semester")
    private String semester;

    @Column(name = "batchCode")
    private String batchCode;


    public Batch() {

    }

    public Batch(String faculty, int year, String semester, String batchCode) {
        this.faculty = faculty;
        this.year = year;
        this.semester = semester;
        this.batchCode = batchCode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    @Override
    public String toString() {
        return "Batch{" +
                "id=" + id +
                ", faculty='" + faculty + '\'' +
                ", year=" + year +
                ", semester='" + semester + '\'' +
                ", batchCode='" + batchCode + '\'' +
                '}';
    }
}
