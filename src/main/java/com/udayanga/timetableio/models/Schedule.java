package com.udayanga.timetableio.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "schedules")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "moduleName")
    private String moduleName;

    @Column(name = "lecturerName")
    private String lecturerName;

    @Column(name = "classRoomID")
    private String classRoomID;

    @Column(name = "batchID")
    private String batchID;

    @Column(name = "startTime")
    private Date startTime;

    @Column(name = "endTime")
    private Date endTime;

    public Schedule() {

    }

	...
}