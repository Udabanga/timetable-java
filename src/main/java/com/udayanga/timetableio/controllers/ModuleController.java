package com.udayanga.timetableio.controllers;

import com.udayanga.timetableio.model.Module;
import com.udayanga.timetableio.repository.ModuleRepository;
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
public class ModuleController {

    @Autowired
    ModuleRepository moduleRepository;

    @GetMapping("/modules")
    public ResponseEntity<List<Module>> getAllModules() {
        try {
            List<Module> cours = new ArrayList<Module>();

            moduleRepository.findAll().forEach(cours::add);

            if (cours.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(cours, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/modules/{id}")
    public ResponseEntity<Module> getModuleById(@PathVariable("id") long id) {
        Optional<Module> moduleData = moduleRepository.findById(id);

        if (moduleData.isPresent()) {
            return new ResponseEntity<>(moduleData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/modules")
    public ResponseEntity<Module> createModule(@RequestBody Module module) {
        try {
            Module _module = moduleRepository
                    .save(new Module(module.getModuleName()));
            return new ResponseEntity<>(_module, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/modules/{id}")
    public ResponseEntity<Module> updateModule(@PathVariable("id") long id, @RequestBody Module module) {
        Optional<Module> moduleData = moduleRepository.findById(id);

        if (moduleData.isPresent()) {
            Module _module = moduleData.get();
            _module.setModuleName(module.getModuleName());
            return new ResponseEntity<>(moduleRepository.save(_module), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    

    @DeleteMapping("/modules/{id}")
    public ResponseEntity<HttpStatus> deleteModule(@PathVariable(value = "id") String id) {
        try {
            moduleRepository.deleteById(Long.parseLong(id));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
