package com.udayanga.timetableio.repository;

import java.util.Optional;

import com.udayanga.timetableio.models.ERole;
import com.udayanga.timetableio.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}