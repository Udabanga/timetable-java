package com.udayanga.timetableio.services;

import com.udayanga.timetableio.model.Batch;
import com.udayanga.timetableio.model.Schedule;
import com.udayanga.timetableio.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ScheduleService {
    List<Schedule> getAllSchedulees();
    void saveSchedule(Schedule schedule);
    Schedule getScheduleById(long id);
    void deleteScheduleById(long id);
    Page<Schedule> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
//    List<Schedule> getScheduleByBatch(Batch batch);
}
