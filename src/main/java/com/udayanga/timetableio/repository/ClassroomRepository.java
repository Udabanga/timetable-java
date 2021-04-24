package com.udayanga.timetableio.repository;

import java.util.List;

import com.udayanga.timetableio.models.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
}
