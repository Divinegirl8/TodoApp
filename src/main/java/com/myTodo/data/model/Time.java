package com.myTodo.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Setter
@Getter
@Entity
public class Time {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long Id;
    private String hour;
    private String minutes;

    @Override
    public String toString() {
        return "Time{" +
                "Id=" + Id +
                ", hour='" + hour + '\'' +
                ", minutes='" + minutes + '\'' +
                '}';
    }
}
