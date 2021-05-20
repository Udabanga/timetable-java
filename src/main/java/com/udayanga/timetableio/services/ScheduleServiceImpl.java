package com.udayanga.timetableio.services;

import com.udayanga.timetableio.model.*;
import com.udayanga.timetableio.repository.ScheduleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public List<Schedule> getAllSchedulees() {
        return scheduleRepository.findAll();
    }

    @Override
    public void saveSchedule(Schedule schedule) {
        this.scheduleRepository.save(schedule);
    }


    @Override
    public Schedule getScheduleById(long id) {
        Optional<Schedule> optional = scheduleRepository.findById(id);
        Schedule schedule = null;
        if (optional.isPresent()) {
            schedule = optional.get();
        } else {
            throw new RuntimeException(" Schedule not found for id :: " + id);
        }
        return schedule;
    }

//    @Override
//    public List<Schedule> getScheduleByBatch(Batch batch) {
//        List<Schedule> optional = scheduleRepository.findByBatch(batch);
//        List<Schedule> schedule = null;
//        if(optional.isEmpty()){
//            throw new RuntimeException(" Schedule not found for batch :: " + batch);
//        }
//        else{
//            schedule=optional;
//        }
//        return schedule;
//    }

    @Override
    public void deleteScheduleById(long id) {
        this.scheduleRepository.deleteById(id);
    }

    @Override
    public Page<Schedule> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.scheduleRepository.findAll(pageable);
    }


}
