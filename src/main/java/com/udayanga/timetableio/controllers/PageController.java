package com.udayanga.timetableio.controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/login")
    public String showLoginPage(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }

        return "redirect:/";
    }

    @GetMapping("/loginSuccessful")
    public String showLoginSuccesfulPage(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }

        return "redirect:/";
    }

    @GetMapping("/admin")
    public String showAdminPage(){
        return "admin";
    }

    @GetMapping("/lecturer")
    public String showLecturerPage(){
        return "lecturer";
    }

    @GetMapping("/batchList")
    public String showBatchList(){
        return "batchList";
    }

    @GetMapping("/viewBatchTimetable")
    public String showViewBatchTimetablePage(){
        return "viewBatchTimetable";
    }

    @GetMapping("/viewLecturerTimetable")
    public String showViewLecturerTimetablePage(){
        return "viewLecturerTimetable";
    }

}
