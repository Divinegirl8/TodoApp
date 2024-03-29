package com.myTodo.data.repository;

import com.myTodo.data.model.Date;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DateRepository extends JpaRepository<Date,Long> {
}
