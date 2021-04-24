package com.udayanga.timetableio.repository;

import java.util.List;

import com.udayanga.timetableio.models.Batch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatchRepository extends JpaRepository<Batch, Long> {
}