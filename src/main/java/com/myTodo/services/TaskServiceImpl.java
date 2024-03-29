package com.myTodo.services;

import com.myTodo.data.model.*;
import com.myTodo.data.repository.*;
import com.myTodo.dtos.request.AddTaskRequest;
import com.myTodo.dtos.response.EndUserResponse;
import com.myTodo.exception.MyTodoException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public Task addTask(AddTaskRequest request,Long userId) throws MyTodoException {
         EndUser endUser = endUserService.findUserBy(userId);

        Date date = new Date();
        date.setDay(request.getDay());
        date.setMonth(request.getMonth());
        date.setYear(request.getYear());
        dateRepository.save(date);


        Time time = new Time();
        time.setHour(request.getHour());
        time.setMinutes(request.getMinutes());
        timeRepository.save(time);



        DueDate dueDate = new DueDate();
        dueDate.setDate(date);
        dueDate.setTime(time);
        dueDateRepository.save(dueDate);


        Task task = new Task();
        task.setMessage(request.getMessage());
        task.setUserId(endUser.getId());
        task.setDueDate(dueDate);
        taskRepository.save(task);


        endUser.getTasks().add(task);
        endUserRepository.save(endUser);


        return task;
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
                break;
            }
        }
    }

    @Override
    public Task findTaskBy(Long id) throws MyTodoException {
        return taskRepository.findById(id).orElseThrow(()->new MyTodoException("no task with " + id + " found"));
    }


}
