package com.udayanga.timetableio.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "classrooms")
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "type")
    private String type;

    @Column(name = "building")
    private String building;

    @Column(name = "floor")
    private int floor;

    @Column(name = "roomNumber")
    private int roomNumber;

    @OneToMany(mappedBy = "classroom")
    private List<Schedule> schedules;

    public Classroom() {
    }

    public Classroom(String type, String building, int floor, int roomNumber) {
        this.type = type;
        this.building = building;
        this.floor = floor;
        this.roomNumber = roomNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", building='" + building + '\'' +
                ", floor=" + floor +
                ", roomNumber=" + roomNumber +
                '}';
    }
}
