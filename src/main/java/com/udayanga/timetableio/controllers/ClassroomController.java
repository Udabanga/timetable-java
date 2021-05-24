package com.udayanga.timetableio.controllers;

import com.udayanga.timetableio.model.Classroom;
import com.udayanga.timetableio.model.Classroom;
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

    @PutMapping("/classrooms/{id}")
    public ResponseEntity<Classroom> updateClassroom(@PathVariable("id") long id, @RequestBody Classroom classroom) {
        Optional<Classroom> classroomData = classroomRepository.findById(id);

        if (classroomData.isPresent()) {
            Classroom _classroom = classroomData.get();
            _classroom.setBuilding(classroom.getBuilding());
            _classroom.setFloor(classroom.getFloor());
            _classroom.setRoomNumber(classroom.getRoomNumber());
            _classroom.setType(classroom.getType());
            return new ResponseEntity<>(classroomRepository.save(_classroom), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/classrooms/{id}")
    public ResponseEntity<HttpStatus> deleteClassroom(@PathVariable(value = "id") String id) {
        try {
            classroomRepository.deleteById(Long.parseLong(id));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
