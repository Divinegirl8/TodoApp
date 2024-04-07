package com.myTodo.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TextNode;
import com.github.fge.jackson.jsonpointer.JsonPointerException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.JsonPatchOperation;
import com.github.fge.jsonpatch.ReplaceOperation;
import com.myTodo.data.model.*;
import com.myTodo.data.repository.*;
import com.myTodo.dtos.request.AddTaskRequest;
import com.myTodo.dtos.request.EditTaskDateRequest;
import com.myTodo.dtos.request.EditTaskMessageRequest;
import com.myTodo.dtos.request.EditTaskTimeRequest;
import com.myTodo.dtos.response.*;
import com.myTodo.exception.MyTodoException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Arrays.stream;

@Service
@AllArgsConstructor
@Transactional
public class TaskServiceImpl implements TaskService{
    private final TaskRepository taskRepository;
    private final EndUserRepository endUserRepository;
    private final TimeRepository timeRepository;
    private final DateRepository dateRepository;
    private final EndUserService endUserService;
    private final DueDateRepository dueDateRepository;

    @Override
    public EndUserResponse createTask() {

        Task task = new Task();
        taskRepository.save(task);
        EndUser endUser = new EndUser();
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        endUser.setTasks(tasks);
        endUserRepository.save(endUser);

        EndUserResponse response = new EndUserResponse();
        response.setId(endUser.getId());
        return response;
    }

    @Override
    public AddTaskResponse addTask(AddTaskRequest request, Long userId) throws MyTodoException {

        validateAddTaskRequest(request);


        EndUser endUser = endUserService.findUserBy(userId);

        Date date = new Date();
        date.setDay(request.getDay().trim());
        date.setMonth(request.getMonth().trim());
        date.setYear(request.getYear().trim());
        dateRepository.save(date);


        Time time = new Time();
        time.setHour(request.getHour().trim());
        time.setMinutes(request.getMinutes().trim());
        timeRepository.save(time);



        DueDate dueDate = new DueDate();
        dueDate.setDate(date);
        dueDate.setTime(time);
        dueDateRepository.save(dueDate);


        Task task = new Task();
        task.setMessage(request.getMessage().trim());
        task.setUserId(endUser.getId());
        task.setDueDate(dueDate);
        taskRepository.save(task);


        endUser.getTasks().add(task);
        endUserRepository.save(endUser);

        AddTaskResponse response = new AddTaskResponse();
        response.setId(task.getId());


        return response;
    }

    @Override
    public void completeTask(Long userId, Long taskId) throws MyTodoException {
        EndUser endUser = endUserService.findUserBy(userId);
        List<Task> userTask = endUser.getTasks();

        for (Task task : userTask) {
            if (task.getId().equals(taskId)) {
                userTask.remove(task);
                taskRepository.delete(task);
                endUserRepository.save(endUser);

            }
        }
    }

    @Override
    public Task findTaskBy(Long id) throws MyTodoException {
        return taskRepository.findById(id).orElseThrow(()->new MyTodoException("no task with " + id + " found"));
    }

    @Override
    public AllTaskResponse allTask(Long userId) throws MyTodoException {
        EndUser endUser = endUserService.findUserBy(userId);
        List<Task> tasks = endUser.getTasks();

        if (tasks.isEmpty()){
            throw new MyTodoException("No task found");
        }

        AllTaskResponse response = new AllTaskResponse();
        response.setTasks(tasks);

        return response;
    }

    @Override
    public EditTaskMessageResponse editTaskMessage(Long userId, Long taskId, EditTaskMessageRequest request) throws MyTodoException {
        EndUser endUser = endUserService.findUserBy(userId);
        List<Task> tasks = endUser.getTasks();
        Task taskValue = null;

        if (tasks.isEmpty()){
            throw new MyTodoException("No task found");
        }


        for (Task task : tasks){
            if (task.getId().equals(taskId)){
                if (request.getMessage() != null){
                task.setMessage(request.getMessage());}
                taskValue = task;

            }
        }

        EditTaskMessageResponse response = new EditTaskMessageResponse();
        response.setTask(taskValue);

        return response;
    }

    @Override
    public EditTaskTimeResponse editTaskTIme(Long userId, Long taskId, Long timeId, EditTaskTimeRequest request) throws MyTodoException {
        EndUser endUser = endUserService.findUserBy(userId);
        List<Task> tasks = endUser.getTasks();

        Task taskValue = null;

        if (tasks.isEmpty()){
            throw new MyTodoException("No task found");
        }




        for (Task task : tasks){
           if (task.getId().equals(taskId) && task.getDueDate().getTime().getId().equals(timeId)){

               Time time =  task.getDueDate().getTime();



               if (request.getHour() != null) {
                   time.setHour(request.getHour());
               }

               if (request.getMinutes() != null) {
                   time.setMinutes(request.getMinutes());
               }

               taskValue = task;

            }
        }

        EditTaskTimeResponse response = new EditTaskTimeResponse();
        response.setTask(taskValue);
        return response;
    }

    @Override
    public EditTaskDateResponse editTaskDate(Long userId, Long taskId, Long dateId, EditTaskDateRequest request) throws MyTodoException {
        EndUser endUser = endUserService.findUserBy(userId);
        List<Task> tasks = endUser.getTasks();

        Task taskValue = null;

        if (tasks.isEmpty()){
            throw new MyTodoException("No task found");
        }


        for (Task task : tasks){
            if (task.getId().equals(taskId) && task.getDueDate().getDate().getId().equals(dateId)){
                Date date = task.getDueDate().getDate();

                if (request.getDay() != null){
                date.setDay(request.getDay());}

                if (request.getMonth() != null){
                date.setMonth(request.getMonth());}

                if (request.getYear() != null){
                date.setYear(request.getYear());}

                taskValue = task;
            }
        }

        EditTaskDateResponse response = new EditTaskDateResponse();
        response.setTask(taskValue);
        return response;
    }

    private void validateAddTaskRequest(AddTaskRequest request) throws MyTodoException {
        if(request.getMessage() == null){
            throw new MyTodoException("{\"msgErr\": \"message field cannot be empty\"}");
        }

        if(request.getDay() == null){
            throw new MyTodoException("{\"dayErr\": \"Day field cannot be empty\"}");
        }

        if(request.getMonth() == null){
            throw new MyTodoException("{\"mntErr\": \"month field cannot be empty\"}");
        }

        if(request.getYear() == null){
            throw new MyTodoException("{\"yrErr\": \"year field cannot be empty\"}");
        }

        if(request.getMinutes() == null){
            throw new MyTodoException("{\"minErr\": \"minutes field cannot be empty\"}");
        }

        if(request.getHour() == null){
            throw new MyTodoException("{\"hrErr\": \"hour field cannot be empty\"}");
        }

    }



}
