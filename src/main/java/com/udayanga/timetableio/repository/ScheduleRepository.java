package com.udayanga.timetableio.repository;

import java.util.List;

import com.udayanga.timetableio.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByStartTime(String startTime);
}
