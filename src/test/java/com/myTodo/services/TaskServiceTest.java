package com.myTodo.services;

import com.myTodo.data.model.Date;
import com.myTodo.data.model.DueDate;
import com.myTodo.data.model.Task;
import com.myTodo.data.model.Time;
import com.myTodo.dtos.request.AddTaskRequest;
import com.myTodo.dtos.response.EndUserResponse;
import com.myTodo.exception.MyTodoException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class TaskServiceTest {
        @Autowired
        private TaskService service;

   @Test void testThatAUserHasOneTaskObject(){
       EndUserResponse response = service.createTask();
       assertThat(response).isNotNull();
       log.info("end user id -> {}",response);
   }

   @Test void  testThatAUserCanAddTask() throws MyTodoException {

       AddTaskRequest request = new AddTaskRequest();
       request.setDay("10");
       request.setYear("2024");
       request.setMonth("10");

       request.setHour("12");
       request.setMinutes("20");
       request.setMessage("code");

       Task task = service.addTask(request,1L);
       assertThat(task).isNotNull();
       log.info("Task -> {}",task);

   }

    @Test void  testThatAUserCanAddTask2() throws MyTodoException {

        AddTaskRequest request = new AddTaskRequest();
        request.setDay("10");
        request.setYear("2024");
        request.setMonth("10");

        request.setHour("12");
        request.setMinutes("20");
        request.setMessage("code java with me vlog");

        Task task = service.addTask(request,1L);
        assertThat(task).isNotNull();
        log.info("Task -> {}",task);

    }


    @Test void testThatUserCanCompleteTask() throws MyTodoException {
      service.completeTask(1L,9L);
      assertThrows(MyTodoException.class,()->service.findTaskBy(9L));
   }
   @Test void testThatAUserHasListOfTasks() throws MyTodoException {
       List<Task> tasks = service.allTask(1L);
       assertThat(tasks).isNotNull();
       log.info("task -> {}",tasks);

   }
}