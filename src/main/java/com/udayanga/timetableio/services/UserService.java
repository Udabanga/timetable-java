package com.udayanga.timetableio.services;

import com.udayanga.timetableio.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    List<User> getAllUseres();
    void saveUser(User user);
    User getUserById(int id);
    void deleteUserById(int id);
    Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
