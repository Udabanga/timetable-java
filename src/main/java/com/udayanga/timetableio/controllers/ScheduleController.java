package com.udayanga.timetableio.controllers;

import com.udayanga.timetableio.model.*;
import com.udayanga.timetableio.model.Schedule;
import com.udayanga.timetableio.repository.ScheduleRepository;
import com.udayanga.timetableio.repository.UserRepository;
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
public class ScheduleController {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    UserRepository userRepository;


    @GetMapping("/schedules")
    public ResponseEntity<List<Schedule>> getAllSchedules(@RequestParam(required = false) String startTime) {
        try {
            List<Schedule> schedules = new ArrayList<Schedule>();

            scheduleRepository.findAll().forEach(schedules::add);

            if (schedules.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(schedules, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/schedules/{id}")
    public ResponseEntity<Schedule> getScheduleById(@PathVariable("id") long id) {
        Optional<Schedule> scheduleData = scheduleRepository.findById(id);

        if (scheduleData.isPresent()) {
            return new ResponseEntity<>(scheduleData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/schedules/view/lecturer/{id}")
    public ResponseEntity<List<Schedule>> getScheduleForLecturer(@PathVariable("id") int id) {
        Optional<User> userData = userRepository.findById(id);

        List<Schedule> scheduleData = scheduleRepository.findByUser(userData.get());

        if (scheduleData.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } else {
            return new ResponseEntity<>(scheduleData, HttpStatus.OK);
        }
    }

    @PostMapping("/schedules/lecturer")
    public ResponseEntity<List<Schedule>> getScheduleByLecturer(@RequestBody List<Search> search) {
        List<Schedule> scheduleData = scheduleRepository.findByUserAndStartTimeGreaterThanAndEndTimeLessThan(search.get(0).getUser(), search.get(0).getStartDate(), search.get(0).getEndDate());

        if (scheduleData.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } else {
            return new ResponseEntity<>(scheduleData, HttpStatus.OK);
        }
    }

    @PostMapping("/schedules/classroom")
    public ResponseEntity<List<Schedule>> getScheduleByClassroom(@RequestBody Search search) {
        List<Schedule> scheduleData = scheduleRepository.findByClassroomAndStartTimeGreaterThanAndEndTimeLessThan(search.getClassroom(), search.getStartDate(), search.getEndDate());

        if (scheduleData.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(scheduleData, HttpStatus.OK);
        }
    }

    @PostMapping("/schedules/batch")
    public ResponseEntity<List<Schedule>> getScheduleByBatch(@RequestBody List<Search> search) {
        List<Schedule> scheduleData = scheduleRepository.findByBatchesAndStartTimeGreaterThanAndEndTimeLessThan(search.get(0).getBatch(), search.get(0).getStartDate(), search.get(0).getEndDate());

        if (scheduleData.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(scheduleData, HttpStatus.OK);
        }
    }

    @PostMapping("/schedules")
    public ResponseEntity<Schedule> createSchedule(@RequestBody Schedule schedule) {
        try {
            Schedule _schedule = scheduleRepository
                    .save(new Schedule(schedule.getDate(), schedule.getEndTime(), schedule.getStartTime(), schedule.getStringDate(), schedule.getStringEndTime(), schedule.getStringStartTime(), schedule.getClassroom(), schedule.getModule(), schedule.getUser(), schedule.getBatches()));
            return new ResponseEntity<>(_schedule, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/schedule/{id}")
    public ResponseEntity<Schedule> updateSchedule(@PathVariable("id") long id, @RequestBody Schedule schedule) {
        Optional<Schedule> scheduleData = scheduleRepository.findById(id);

        if (scheduleData.isPresent()) {
            Schedule _schedule = scheduleData.get();
            _schedule.setDate(schedule.getDate());
            _schedule.setEndTime(schedule.getEndTime());
            _schedule.setStartTime(schedule.getStartTime());
            _schedule.setStringDate(schedule.getStringDate());
            _schedule.setStringEndTime(schedule.getStringEndTime());
            _schedule.setStringStartTime(schedule.getStringStartTime());
            _schedule.setClassroom(schedule.getClassroom());
            _schedule.setModule(schedule.getModule());
            _schedule.setUser(schedule.getUser());
            _schedule.setBatches(schedule.getBatches());
            return new ResponseEntity<>(scheduleRepository.save(_schedule), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/schedules/{id}")
    public ResponseEntity<HttpStatus> deleteSchedule(@PathVariable(value = "id") String id) {
        try {
            scheduleRepository.deleteById(Long.parseLong(id));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}