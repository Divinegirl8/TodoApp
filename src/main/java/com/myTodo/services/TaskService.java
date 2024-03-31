package com.myTodo.services;

import com.myTodo.data.model.Task;
import com.myTodo.dtos.request.AddTaskRequest;
import com.myTodo.dtos.request.EditTaskDateRequest;
import com.myTodo.dtos.request.EditTaskMessageRequest;
import com.myTodo.dtos.request.EditTaskTimeRequest;
import com.myTodo.dtos.response.*;
import com.myTodo.exception.MyTodoException;

import java.util.List;

public interface TaskService {
    EndUserResponse createTask();
    AddTaskResponse addTask(AddTaskRequest request, Long userId) throws MyTodoException;
    void completeTask(Long userId,Long taskId) throws MyTodoException;
    Task findTaskBy(Long id) throws MyTodoException;
    AllTaskResponse allTask(Long userId) throws MyTodoException;

    EditTaskMessageResponse editTaskMessage(Long userId, Long taskId, EditTaskMessageRequest request) throws MyTodoException;

    EditTaskTimeResponse editTaskTIme(Long userId, Long taskId, Long timeId, EditTaskTimeRequest request) throws MyTodoException;
    EditTaskDateResponse editTaskDate(Long userId, Long taskId, Long dateId, EditTaskDateRequest request) throws MyTodoException;


}
