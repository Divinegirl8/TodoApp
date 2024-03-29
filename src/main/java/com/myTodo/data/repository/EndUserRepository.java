package com.myTodo.data.repository;

import com.myTodo.data.model.EndUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EndUserRepository extends JpaRepository<EndUser,Long> {
}
