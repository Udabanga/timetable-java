package com.udayanga.timetableio.repository;

import com.udayanga.timetableio.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findByUser(User userId);

    List<Schedule> findByUserAndStartTimeGreaterThanAndEndTimeLessThan(User user, Date startTime, Date endTime);

    List<Schedule> findByBatches(Batch batch);

    List<Schedule> findByBatchesAndStartTimeGreaterThanAndEndTimeLessThan(Batch batch, Date startTime, Date endTime);

    List<Schedule> findByClassroomAndStartTimeGreaterThanAndEndTimeLessThan(Classroom classroom, Date startTime, Date endTime);

    List<Schedule> findAllByStartTimeGreaterThanAndEndTimeLessThan(Date startTime, Date endTime);

}