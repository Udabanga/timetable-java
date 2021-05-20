package com.udayanga.timetableio.services;

import com.udayanga.timetableio.model.Module;
import com.udayanga.timetableio.repository.ModuleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    @Override
    public List<Module> getAllModulees() {
        return moduleRepository.findAll();
    }

    @Override
    public void saveModule(Module module) {
        this.moduleRepository.save(module);
    }

    @Override
    public Module getModuleById(long id) {
        Optional<Module> optional = moduleRepository.findById(id);
        Module module = null;
        if (optional.isPresent()) {
            module = optional.get();
        } else {
            throw new RuntimeException(" Module not found for id :: " + id);
        }
        return module;
    }

    @Override
    public void deleteModuleById(long id) {
        this.moduleRepository.deleteById(id);
    }

    @Override
    public Page<Module> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.moduleRepository.findAll(pageable);
    }
}
