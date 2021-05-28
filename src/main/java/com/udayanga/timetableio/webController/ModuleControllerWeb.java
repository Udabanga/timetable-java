package com.udayanga.timetableio.webController;

import com.udayanga.timetableio.model.Module;
import com.udayanga.timetableio.services.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ModuleControllerWeb {
    @Autowired
    private ModuleService moduleService;

    @GetMapping("/admin/module/add")
    public String adminModuleAdd(Model model) {
        // create model attribute to bind form data
        Module module = new Module();
        model.addAttribute("module", module);
        return "moduleListAdd";
    }

    @PostMapping("/saveModule")
    public String saveModule(@ModelAttribute("module") Module module) {
        // save module to database
        moduleService.saveModule(module);
        return "redirect:/admin/module";
    }

    @GetMapping("/admin/module/update/{id}")
    public String adminModuleUpdate(@PathVariable(value = "id") long id, Model model) {

        // get module from the service
        Module module = moduleService.getModuleById(id);

        // set module as a model attribute to pre-populate the form
        model.addAttribute("module", module);
        return "moduleListUpdate";
    }

    @GetMapping("/deleteModule/{id}")
    public String deleteModule(@PathVariable(value = "id") long id) {

        // call delete module method
        this.moduleService.deleteModuleById(id);
        return "redirect:/admin/module";
    }

}
