package com.udayanga.timetableio.repository;

import com.udayanga.timetableio.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}
