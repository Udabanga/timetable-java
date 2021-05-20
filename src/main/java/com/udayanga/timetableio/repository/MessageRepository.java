package com.udayanga.timetableio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udayanga.timetableio.model.Message;

public interface MessageRepository extends JpaRepository<Message, Integer>{

}
