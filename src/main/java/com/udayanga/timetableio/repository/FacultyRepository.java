package com.udayanga.timetableio.repository;

import java.util.List;

import com.udayanga.timetableio.models.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}
