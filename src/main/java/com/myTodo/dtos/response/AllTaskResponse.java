package com.myTodo.dtos.response;

import com.myTodo.data.model.Task;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class AllTaskResponse {
    private List<Task> tasks;


}
