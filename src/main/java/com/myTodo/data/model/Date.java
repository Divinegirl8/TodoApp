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
public class Date {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String year;
    private String month;
    private String day;

    @Override
    public String toString() {
        return "Date{" +
                "id=" + id +
                ", year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", day='" + day + '\'' +
                '}';
    }
}
