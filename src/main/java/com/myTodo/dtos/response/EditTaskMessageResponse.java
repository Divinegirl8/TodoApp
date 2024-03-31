package com.myTodo.dtos.response;

import com.myTodo.data.model.Task;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class EditTaskMessageResponse {
    private Task task;
}
