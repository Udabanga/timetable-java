package com.udayanga.timetableio.repository;

import com.udayanga.timetableio.model.ERole;
import com.udayanga.timetableio.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}