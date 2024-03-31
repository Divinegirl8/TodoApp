package com.myTodo.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EditTaskTimeRequest {
    private String hour;
    private String minutes;
}
