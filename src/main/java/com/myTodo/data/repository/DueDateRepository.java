package com.myTodo.data.repository;

import com.myTodo.data.model.DueDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DueDateRepository extends JpaRepository<DueDate,Long> {
}
