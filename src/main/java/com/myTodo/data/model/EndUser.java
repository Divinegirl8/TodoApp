package com.myTodo.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Setter
@Getter
@Entity
public class EndUser {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @OneToMany
    private List<Task> tasks;

    @Override
    public String toString() {
        return "EndUser{" +
                "id=" + id +
                ", task=" + tasks +
                '}';
    }
}
