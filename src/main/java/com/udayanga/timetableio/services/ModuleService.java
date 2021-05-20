package com.udayanga.timetableio.services;

import com.udayanga.timetableio.model.Module;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ModuleService {
    List<Module> getAllModulees();
    void saveModule(Module module);
    Module getModuleById(long id);
    void deleteModuleById(long id);
    Page<Module> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
