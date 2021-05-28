package com.udayanga.timetableio.controllers;

import com.udayanga.timetableio.model.Faculty;
import com.udayanga.timetableio.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http:/localhost:8081")
@RestController
@RequestMapping("/api")
public class FacultyController {

    @Autowired
    FacultyRepository facultyRepository;

    @GetMapping("/faculties")
    public ResponseEntity<List<Faculty>> getAllFaculties() {
        try {
            List<Faculty> faculties = new ArrayList<Faculty>();

            facultyRepository.findAll().forEach(faculties::add);

            if (faculties.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(faculties, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/faculties/{id}")
    public ResponseEntity<Faculty> getFacultyById(@PathVariable("id") long id) {
        Optional<Faculty> facultyData = facultyRepository.findById(id);

        if (facultyData.isPresent()) {
            return new ResponseEntity<>(facultyData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/faculties")
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty) {
        try {
            Faculty _faculty = facultyRepository
                    .save(new Faculty(faculty.getFacultyName()));
            return new ResponseEntity<>(_faculty, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
