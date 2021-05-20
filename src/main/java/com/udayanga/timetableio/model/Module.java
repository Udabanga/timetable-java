package com.udayanga.timetableio.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "modules")
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "moduleName")
    private String moduleName;

    @OneToMany(mappedBy = "module")
    private List<Schedule> schedules;


    public Module() {
    }

    public Module(String moduleName) {
        this.moduleName = moduleName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    @Override
    public String toString() {
        return "Module{" +
                "id=" + id +
                ", moduleName='" + moduleName + '\'' +
                '}';
    }
}
