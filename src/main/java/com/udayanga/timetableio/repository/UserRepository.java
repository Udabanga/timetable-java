package com.udayanga.timetableio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udayanga.timetableio.model.User;

public interface UserRepository extends JpaRepository<User, Integer>
{

	Optional<User> findByEmail(String email);

	Optional<User> findByName(String name);


	Boolean existsByEmail(String email);

}
