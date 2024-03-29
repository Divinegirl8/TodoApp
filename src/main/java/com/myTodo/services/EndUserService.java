package com.myTodo.services;

import com.myTodo.data.model.EndUser;
import com.myTodo.exception.MyTodoException;

public interface EndUserService {
    EndUser findUserBy(Long id) throws MyTodoException;
}
