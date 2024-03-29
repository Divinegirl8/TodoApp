package com.myTodo.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
public class DueDate {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @OneToOne
    private Date date;
    @OneToOne
    private Time time;

    @Override
    public String toString() {
        return "DueDate{" +
                "id=" + id +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}
