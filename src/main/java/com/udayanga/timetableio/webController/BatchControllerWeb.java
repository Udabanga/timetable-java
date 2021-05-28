package com.udayanga.timetableio.webController;

import com.udayanga.timetableio.model.Batch;
import com.udayanga.timetableio.services.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BatchControllerWeb {
    @Autowired
    private BatchService batchService;

    @GetMapping("/admin/batch/add")
    public String adminBatchAdd(Model model) {
        // create model attribute to bind form data
        Batch batch = new Batch();
        model.addAttribute("batch", batch);
        return "batchListAdd";
    }

    @PostMapping("/saveBatch")
    public String saveBatch(@ModelAttribute("batch") Batch batch) {
        // save batch to database
        batchService.saveBatch(batch);
        return "redirect:/admin/batch";
    }

    @GetMapping("/admin/batch/update/{id}")
    public String adminBatchUpdate(@PathVariable ( value = "id") long id, Model model) {

        // get batch from the service
        Batch batch = batchService.getBatchById(id);

        // set batch as a model attribute to pre-populate the form
        model.addAttribute("batch", batch);
        return "batchListUpdate";
    }

    @GetMapping("/deleteBatch/{id}")
    public String deleteBatch(@PathVariable (value = "id") long id) {

        // call delete batch method
        this.batchService.deleteBatchById(id);
        return "redirect:/admin/batch";
    }
}
