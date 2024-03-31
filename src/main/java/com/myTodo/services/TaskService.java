package com.myTodo.services;

import com.myTodo.data.model.Task;
import com.myTodo.dtos.request.AddTaskRequest;
import com.myTodo.dtos.request.EditTaskMessageRequest;
import com.myTodo.dtos.response.AllTaskResponse;
import com.myTodo.dtos.response.EndUserResponse;
import com.myTodo.exception.MyTodoException;

import java.util.List;

public interface TaskService {
    EndUserResponse createTask();
    Task addTask(AddTaskRequest request,Long userId) throws MyTodoException;
    void completeTask(Long userId,Long taskId) throws MyTodoException;
    Task findTaskBy(Long id) throws MyTodoException;
    AllTaskResponse allTask(Long userId) throws MyTodoException;

    Task editTaskMessage(Long userId, Long taskId, EditTaskMessageRequest request) throws MyTodoException;


}
