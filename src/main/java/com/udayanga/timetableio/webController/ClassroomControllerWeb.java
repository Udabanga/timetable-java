package com.udayanga.timetableio.webController;

import com.udayanga.timetableio.model.Classroom;
import com.udayanga.timetableio.services.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClassroomControllerWeb {
    @Autowired
    private ClassroomService classroomService;

    @GetMapping("/admin/classroom/add")
    public String adminClassroomAdd(Model model) {
        // create model attribute to bind form data
        Classroom classroom = new Classroom();
        model.addAttribute("classroom", classroom);
        return "classroomListAdd";
    }

    @PostMapping("/saveClassroom")
    public String saveClassroom(@ModelAttribute("classroom") Classroom classroom) {
        // save classroom to database
        classroomService.saveClassroom(classroom);
        return "redirect:/admin/classroom";
    }

    @GetMapping("/admin/classroom/update/{id}")
    public String adminClassroomUpdate(@PathVariable(value = "id") long id, Model model) {

        // get classroom from the service
        Classroom classroom = classroomService.getClassroomById(id);

        // set classroom as a model attribute to pre-populate the form
        model.addAttribute("classroom", classroom);
        return "classroomListUpdate";
    }

    @GetMapping("/deleteClassroom/{id}")
    public String deleteClassroom(@PathVariable(value = "id") long id) {

        // call delete classroom method
        this.classroomService.deleteClassroomById(id);
        return "redirect:/admin/classroom";
    }
}
