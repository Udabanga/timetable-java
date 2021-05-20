package com.udayanga.timetableio.services;

import com.udayanga.timetableio.model.Batch;
import com.udayanga.timetableio.repository.BatchRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BatchServiceImpl implements BatchService {

    @Autowired
    private BatchRepository batchRepository;

    @Override
    public List<Batch> getAllBatches() {
        return batchRepository.findAll();
    }

    @Override
    public void saveBatch(Batch batch) {
        this.batchRepository.save(batch);
    }

    @Override
    public Batch getBatchById(long id) {
        Optional<Batch> optional = batchRepository.findById(id);
        Batch batch = null;
        if (optional.isPresent()) {
            batch = optional.get();
        } else {
            throw new RuntimeException(" Batch not found for id :: " + id);
        }
        return batch;
    }

    @Override
    public void deleteBatchById(long id) {
        this.batchRepository.deleteById(id);
    }

    @Override
    public Page<Batch> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.batchRepository.findAll(pageable);
    }
}
