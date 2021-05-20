package com.udayanga.timetableio.repository;

import com.udayanga.timetableio.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleRepository extends JpaRepository<Module, Long> {
}
