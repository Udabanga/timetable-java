package com.udayanga.timetableio.controllers;

import com.udayanga.timetableio.model.Batch;
import com.udayanga.timetableio.repository.BatchRepository;
import com.udayanga.timetableio.services.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class BatchController {

    @Autowired
    BatchRepository batchRepository;

    @GetMapping("/batches")
    public ResponseEntity<List<Batch>> getAllBatches() {
        try {
            List<Batch> batches = new ArrayList<Batch>();

            batchRepository.findAll().forEach(batches::add);

            if (batches.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(batches, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/batches/{id}")
    public ResponseEntity<Batch> getBatchById(@PathVariable("id") long id) {
        Optional<Batch> batchData = batchRepository.findById(id);

        if (batchData.isPresent()) {
            return new ResponseEntity<>(batchData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/batches")
    public ResponseEntity<Batch> createBatch(@RequestBody Batch batch) {
        try {
            Batch _batch = batchRepository
                    .save(new Batch(batch.getFaculty(), batch.getYear(), batch.getSemester(), batch.getBatchCode()));
            return new ResponseEntity<>(_batch, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    //Web
//    @Autowired
//    private BatchService batchService;
//
//    // display list of batchs
////	@GetMapping("/")
////	public String viewHomePage(Model model) {
////		return findPaginated(1, "firstName", "asc", model);
////	}
//
//    @GetMapping("/showNewBatchForm")
//    public String showNewBatchForm(Model model) {
//        // create model attribute to bind form data
//        Batch batch = new Batch();
//        model.addAttribute("batch", batch);
//        return "new_batch";
//    }
//
//    @PostMapping("/saveBatch")
//    public String saveBatch(@ModelAttribute("batch") Batch batch) {
//        // save batch to database
//        batchService.saveBatch(batch);
//        return "redirect:/admin/batchList";
//    }
//
//    @GetMapping("/showFormForUpdateBatch/{id}")
//    public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
//
//        // get batch from the service
//        Batch batch = batchService.getBatchById(id);
//
//        // set batch as a model attribute to pre-populate the form
//        model.addAttribute("batch", batch);
//        return "update_batch";
//    }
//
//    @GetMapping("/deleteBatch/{id}")
//    public String deleteBatch(@PathVariable (value = "id") long id) {
//
//        // call delete batch method
//        this.batchService.deleteBatchById(id);
//        return "redirect:/admin/batchList";
//    }
//
//
//    @GetMapping("/page/{pageNo}")
//    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
//                                @RequestParam("sortField") String sortField,
//                                @RequestParam("sortDir") String sortDir,
//                                Model model) {
//        int pageSize = 5;
//
//        Page<Batch> page = batchService.findPaginated(pageNo, pageSize, sortField, sortDir);
//        List<Batch> listBatchs = page.getContent();
//
//        model.addAttribute("currentPage", pageNo);
//        model.addAttribute("totalPages", page.getTotalPages());
//        model.addAttribute("totalItems", page.getTotalElements());
//
//        model.addAttribute("sortField", sortField);
//        model.addAttribute("sortDir", sortDir);
//        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
//
//        model.addAttribute("listBatchs", listBatchs);
//        return "batchList";
//    }
}
