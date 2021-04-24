package com.udayanga.timetableio.repository;

import java.util.List;

import com.udayanga.timetableio.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
