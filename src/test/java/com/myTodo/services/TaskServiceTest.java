package com.myTodo.services;

import com.myTodo.data.model.Date;
import com.myTodo.data.model.DueDate;
import com.myTodo.data.model.Task;
import com.myTodo.data.model.Time;
import com.myTodo.dtos.request.AddTaskRequest;
import com.myTodo.dtos.request.EditTaskDateRequest;
import com.myTodo.dtos.request.EditTaskMessageRequest;
import com.myTodo.dtos.request.EditTaskTimeRequest;
import com.myTodo.dtos.response.*;
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

       AddTaskResponse task = service.addTask(request,1L);
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

        AddTaskResponse task = service.addTask(request,1L);
        assertThat(task).isNotNull();
        log.info("Task -> {}",task);

    }


    @Test void testThatUserCanCompleteTask() throws MyTodoException {
      service.completeTask(1L,9L);
      assertThrows(MyTodoException.class,()->service.findTaskBy(9L));
   }
   @Test void testThatAUserHasListOfTasks() throws MyTodoException {
       AllTaskResponse tasks = service.allTask(1L);
       assertThat(tasks).isNotNull();
       log.info("task -> {}",tasks);

   }
   @Test void testThatAUserCanEditTaskMessage() throws MyTodoException {
       EditTaskMessageRequest request = new EditTaskMessageRequest();
       request.setMessage("edit message");
       EditTaskMessageResponse task = service.editTaskMessage(1L,10L,request);
       assertThat(task).isNotNull();
       log.info("task -> {}",task);
   }

   @Test void testThatAUserCanEditTaskTime() throws MyTodoException {
       EditTaskTimeRequest request = new EditTaskTimeRequest();
       request.setHour("00");
       request.setMinutes("00");
       EditTaskTimeResponse task =  service.editTaskTIme(1L,11L,8L,request);
       assertThat(task).isNotNull();
       log.info("task -> {}",task);
   }

   @Test void  testThatAUserCanEditTaskDate() throws MyTodoException {
       EditTaskDateRequest request = new EditTaskDateRequest();
       request.setDay("01");
       request.setMonth("10");
       request.setYear("1999");

       EditTaskDateResponse response = service.editTaskDate(1L,10L,7L,request);
       assertThat(response).isNotNull();
       log.info("task -> {}",response);
   }

}