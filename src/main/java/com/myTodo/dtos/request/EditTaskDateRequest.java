package com.myTodo.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EditTaskDateRequest {
    private String day;
    private String month;
    private String year;

}
