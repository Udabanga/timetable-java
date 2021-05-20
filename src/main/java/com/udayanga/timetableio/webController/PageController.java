package com.udayanga.timetableio.webController;

import com.udayanga.timetableio.model.*;

import com.udayanga.timetableio.repository.*;
import com.udayanga.timetableio.security.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PageController {

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;


//	private Authentication userAuth;

    @RequestMapping("/success")
    public void loginPageRedirect(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException, ServletException {

        String role = authResult.getAuthorities().toString();
//		userAuth = authResult;

        if (role.contains("ROLE_ADMIN")) {
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/admin"));
        } else if (role.contains("ROLE_LECTURER")) {
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/lecturer"));
        }
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

//	@GetMapping("/admin/lecturerList")
//	public String lecturerList()
//	{
//		return "lecturerList";
//	}

    @GetMapping("/admin/lecturerList")
    public String lecturerList(Model model) {
        model.addAttribute("listUsers", userRepository.findAll());
        User user = new User();
        model.addAttribute("batch", user);
        return "lecturerList";
    }

    @GetMapping("/admin/batchList")
    public String batchList(Model model) {
        model.addAttribute("listBatches", batchRepository.findAll());
        Batch batch = new Batch();
        model.addAttribute("batch", batch);
        return "batchList";
    }

    @GetMapping("/admin/moduleList")
    public String moduleList(Model model) {
        model.addAttribute("listModules", moduleRepository.findAll());
        Module modules = new Module();
        model.addAttribute("modules", modules);
        return "moduleList";
    }

    @GetMapping("/admin/classroomList")
    public String classroomList(Model model) {
        model.addAttribute("listClassrooms", classroomRepository.findAll());
        Classroom classrooms = new Classroom();
        model.addAttribute("classrooms", classrooms);
        return "classroomList";
    }

    @GetMapping("/lecturer")
    public String lecturer(Model model, Authentication authResult) {
        User userLecturer = ((User) ((AuthenticatedUser) authResult.getPrincipal()).getUser());
//		User userLecturer = ((AuthenticatedUser)userAuth.getPrincipal()).getUser();
        model.addAttribute("user", userLecturer);
        return "lecturer";
    }

    @GetMapping("/lecturer/schedule")
    public String lecturerSchedule(Model model, Authentication authResult) {
        User userID = ((User) ((AuthenticatedUser) authResult.getPrincipal()).getUser());
//		User userID = ((AuthenticatedUser)userAuth.getPrincipal()).getUser();
        model.addAttribute("listSchedules", scheduleRepository.findByUser(userID));
        return "lecturerViewTimetable";
    }

    @GetMapping("/batchTimetable")
    public String batchTimetable(Model model) {
        Search search = new Search();
        model.addAttribute("search", search);
        model.addAttribute("listBatches", batchRepository.findAll());
        model.addAttribute("listSchedules", scheduleRepository.findAll());
        return "viewBatchTimetable";
    }

    @PostMapping("/searchBatchSchedule")
    public String searchBatchSchedule(@ModelAttribute("search") Search search, Model model) {
        model.addAttribute("listBatches", batchRepository.findAll());
        model.addAttribute("listSchedules", scheduleRepository.findByBatchesAndStartTimeGreaterThanAndEndTimeLessThan(search.getBatch(), search.getStartDate(), search.getEndDate()));
        return "viewBatchTimetable";
    }

    @GetMapping("/lecturerTimetable")
    public String lecturerTimetable(Model model) {
        Search search = new Search();
        model.addAttribute("search", search);
        model.addAttribute("listLecturers", userRepository.findAll());
        model.addAttribute("listSchedules", scheduleRepository.findAll());
        return "viewLecturerTimetable";
    }

    @PostMapping("/searchLecturerSchedule")
    public String searchLecturerSchedule(@ModelAttribute("search") Search search, Model model) {
        model.addAttribute("listLecturers", userRepository.findAll());
        model.addAttribute("listSchedules", scheduleRepository.findByUserAndStartTimeGreaterThanAndEndTimeLessThan(search.getUser(), search.getStartDate(), search.getEndDate()));
        return "viewLecturerTimetable";
    }
}
