package com.udayanga.timetableio.services;

import com.udayanga.timetableio.model.Classroom;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClassroomService {
	List<Classroom> getAllClassroomes();
	void saveClassroom(Classroom classroom);
	Classroom getClassroomById(long id);
	void deleteClassroomById(long id);
	Page<Classroom> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
