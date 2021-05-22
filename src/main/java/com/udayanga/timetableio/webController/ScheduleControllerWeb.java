package com.udayanga.timetableio.webController;

import com.udayanga.timetableio.model.*;
import com.udayanga.timetableio.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ScheduleControllerWeb {
    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private UserService userService;

    @Autowired
    private BatchService batchService;

    @GetMapping("/showNewScheduleForm")
    public String showNewScheduleForm(Model model) {
        // create model attribute to bind form data
        Schedule schedule = new Schedule();
        model.addAttribute("schedule", schedule);

        model.addAttribute("listClassrooms", classroomService.getAllClassroomes());
        model.addAttribute("listModules", moduleService.getAllModulees());
        model.addAttribute("listLecturers", userService.getAllUseres());
        model.addAttribute("listBatches", batchService.getAllBatches());
        return "scheduleListAdd";
    }

    @PostMapping("/saveSchedule")
    public String saveSchedule(@ModelAttribute("schedule") Schedule schedule, Authentication authResult) throws ParseException {
        // save schedule to database
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(schedule.getStringDate());

        Date startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(schedule.getStringDate()+" "+ schedule.getStringStartTime());
        Date endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(schedule.getStringDate()+" "+ schedule.getStringEndTime());

        schedule.setDate(date);;
        schedule.setStartTime(startTime);
        schedule.setEndTime(endTime);
        scheduleService.saveSchedule(schedule);

        String role =  authResult.getAuthorities().toString();

        if(role.contains("ROLE_ADMIN")){
            return "admin";
        }
        else if(role.contains("ROLE_LECTURER")) {
            return "lecturer";
        }
        else{
            return "login";
        }
    }

    @GetMapping("/showFormForUpdateSchedule/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

        // get schedule from the service
        Schedule schedule = scheduleService.getScheduleById(id);

        // set schedule as a model attribute to pre-populate the form
        model.addAttribute("schedule", schedule);
        return "scheduleListUpdate";
    }

    @GetMapping("/deleteSchedule/{id}")
    public String deleteSchedule(@PathVariable(value = "id") long id) {

        // call delete schedule method
        this.scheduleService.deleteScheduleById(id);
        return "redirect:/admin/scheduleList";
    }

}
