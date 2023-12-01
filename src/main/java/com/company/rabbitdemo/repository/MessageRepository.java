package com.company.rabbitdemo.repository;

import com.company.rabbitdemo.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {
}
