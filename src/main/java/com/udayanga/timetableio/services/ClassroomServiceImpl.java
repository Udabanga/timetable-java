package com.udayanga.timetableio.services;

import com.udayanga.timetableio.model.Classroom;
import com.udayanga.timetableio.repository.ClassroomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassroomServiceImpl implements ClassroomService {

	@Autowired
	private ClassroomRepository classroomRepository;

	@Override
	public List<Classroom> getAllClassroomes() {
		return classroomRepository.findAll();
	}

	@Override
	public void saveClassroom(Classroom classroom) {
		this.classroomRepository.save(classroom);
	}

	@Override
	public Classroom getClassroomById(long id) {
		Optional<Classroom> optional = classroomRepository.findById(id);
		Classroom classroom = null;
		if (optional.isPresent()) {
			classroom = optional.get();
		} else {
			throw new RuntimeException(" Classroom not found for id :: " + id);
		}
		return classroom;
	}

	@Override
	public void deleteClassroomById(long id) {
		this.classroomRepository.deleteById(id);
	}

	@Override
	public Page<Classroom> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
				Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.classroomRepository.findAll(pageable);
	}
}
