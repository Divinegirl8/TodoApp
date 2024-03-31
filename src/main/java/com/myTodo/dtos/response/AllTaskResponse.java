package com.myTodo.dtos.response;

import com.myTodo.data.model.Task;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AllTaskResponse {
    private List<Task> tasks;
}
