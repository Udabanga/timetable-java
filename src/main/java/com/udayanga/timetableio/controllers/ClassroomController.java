package com.udayanga.timetableio.controllers;

import com.udayanga.timetableio.models.Classroom;
import com.udayanga.timetableio.repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ClassroomController {

    @Autowired
    ClassroomRepository classroomRepository;

    @GetMapping("/classrooms")
    public ResponseEntity<List<Classroom>> getAllClassrooms() {
        try {
            List<Classroom> classrooms = new ArrayList<Classroom>();

            classroomRepository.findAll().forEach(classrooms::add);

            if (classrooms.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(classrooms, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/classrooms/{id}")
    public ResponseEntity<Classroom> getClassroomById(@PathVariable("id") long id) {
        Optional<Classroom> classroomData = classroomRepository.findById(id);

        if (classroomData.isPresent()) {
            return new ResponseEntity<>(classroomData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/classrooms")
    public ResponseEntity<Classroom> createClassroom(@RequestBody Classroom classroom) {
        try {
            Classroom _classroom = classroomRepository
                    .save(new Classroom(classroom.getType(), classroom.getBuilding(), classroom.getFloor(), classroom.getRoomNumber()));
            return new ResponseEntity<>(_classroom, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
