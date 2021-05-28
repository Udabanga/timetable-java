package com.udayanga.timetableio.controllers;

import com.udayanga.timetableio.model.Batch;
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

@CrossOrigin(origins = "http:/localhost:8081")
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

    @PutMapping("/batches/{id}")
    public ResponseEntity<Batch> updateBatch(@PathVariable("id") long id, @RequestBody Batch batch) {
        Optional<Batch> batchData = batchRepository.findById(id);

        if (batchData.isPresent()) {
            Batch _batch = batchData.get();
            _batch.setBatchCode(batch.getBatchCode());
            _batch.setFaculty(batch.getFaculty());
            _batch.setSemester(batch.getSemester());
            _batch.setYear(batch.getYear());
            return new ResponseEntity<>(batchRepository.save(_batch), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/batches/{id}")
    public ResponseEntity<HttpStatus> deleteBatch(@PathVariable(value = "id") String id) {
        try {
            batchRepository.deleteById(Long.parseLong(id));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
