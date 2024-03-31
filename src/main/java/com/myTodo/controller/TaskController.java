package com.myTodo.controller;

import com.myTodo.dtos.request.AddTaskRequest;
import com.myTodo.dtos.request.EditTaskDateRequest;
import com.myTodo.dtos.request.EditTaskMessageRequest;
import com.myTodo.dtos.request.EditTaskTimeRequest;
import com.myTodo.dtos.response.*;
import com.myTodo.exception.MyTodoException;
import com.myTodo.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class TaskController {
    @Autowired
    TaskService service;

    @PostMapping("/createTask")
    public ResponseEntity<?> createTask(){
        try {
            EndUserResponse response = service.createTask();
            return ResponseEntity.ok().body(response);
        }catch (Exception exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PostMapping("/addTask/{id}")
    public ResponseEntity<?> addTask(@RequestBody AddTaskRequest request, @PathVariable("id") Long id){
        try {
            AddTaskResponse response = service.addTask(request,id);
            return ResponseEntity.ok().body(response);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping ("/completeTask/{userId}/{taskId}")
    public ResponseEntity<?> completeTask(@PathVariable("userId") Long userId, @PathVariable("taskId") Long taskId) {
        try {
            service.completeTask(userId, taskId);
            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @GetMapping("/allTask{userId}")
    public ResponseEntity<?> allTask(@PathVariable("userId") Long userId){
        try {
            AllTaskResponse response = service.allTask(userId);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/editMessage/{userId}/{taskId}")
    public ResponseEntity<?> editMessage(@PathVariable("userId") Long userId, @PathVariable("taskId") Long taskId, @RequestBody EditTaskMessageRequest request){
        try {
            EditTaskMessageResponse response = service.editTaskMessage(userId, taskId, request);
            return ResponseEntity.ok().body(response);
        }catch (Exception exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PatchMapping("/editTime/{userId}/{taskId}/{timeId}")
    public ResponseEntity<?> editTime(@PathVariable("userId") Long userId, @PathVariable("taskId") Long taskId, @PathVariable("timeId") Long timeId, EditTaskTimeRequest request){
        try {
            EditTaskTimeResponse response = service.editTaskTIme(userId, taskId, timeId, request);
            return ResponseEntity.ok().body(response);
        } catch (MyTodoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/editDate/{userId}/{taskId}/{dateId}")
    public ResponseEntity<?> editDate(@PathVariable("userId") Long userId, @PathVariable("taskId") Long taskId, @PathVariable("dateId") Long timeId, EditTaskDateRequest request){
        try {
            EditTaskDateResponse response = service.editTaskDate(userId, taskId, timeId, request);
            return ResponseEntity.ok().body(response);
        } catch (MyTodoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
