package com.myTodo.dtos.response;

import com.myTodo.data.model.Task;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class EditTaskTimeResponse {
    private Task task;
}
