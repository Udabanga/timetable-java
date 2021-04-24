package com.udayanga.timetableio.controllers;

import com.udayanga.timetableio.models.Course;
import com.udayanga.timetableio.repository.CourseRepository;
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
public class CourseController {

    @Autowired
    CourseRepository courseRepository;

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getAllCourses() {
        try {
            List<Course> courses = new ArrayList<Course>();

            courseRepository.findAll().forEach(courses::add);

            if (courses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable("id") long id) {
        Optional<Course> courseData = courseRepository.findById(id);

        if (courseData.isPresent()) {
            return new ResponseEntity<>(courseData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/courses")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        try {
            Course _course = courseRepository
                    .save(new Course(course.getCourseName()));
            return new ResponseEntity<>(_course, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
