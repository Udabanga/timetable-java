package com.udayanga.timetableio.services;

import com.udayanga.timetableio.model.Batch;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BatchService {
    List<Batch> getAllBatches();
    void saveBatch(Batch batch);
    Batch getBatchById(long id);
    void deleteBatchById(long id);
    Page<Batch> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
