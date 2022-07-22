package com.example.ToDoList.repository;

import com.example.ToDoList.entity.ToDoEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ToDoRepo extends CrudRepository<ToDoEntity, Long> {
    ToDoEntity findByTitle(String title);
    Optional<ToDoEntity> findById(Long id);
}
