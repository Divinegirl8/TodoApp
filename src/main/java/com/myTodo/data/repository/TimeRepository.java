package com.myTodo.data.repository;

import com.myTodo.data.model.Time;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeRepository extends JpaRepository<Time,Long> {
}
