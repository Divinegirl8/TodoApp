package com.myTodo.services;

import com.myTodo.data.model.Task;
import com.myTodo.dtos.request.AddTaskRequest;
import com.myTodo.dtos.response.EndUserResponse;
import com.myTodo.exception.MyTodoException;

public interface TaskService {
    EndUserResponse createTask();
    Task addTask(AddTaskRequest request,Long userId) throws MyTodoException;
    void completeTask(Long userId,Long taskId) throws MyTodoException;
    Task findTaskBy(Long id) throws MyTodoException;
}
