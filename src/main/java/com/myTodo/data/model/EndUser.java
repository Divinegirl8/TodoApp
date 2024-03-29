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
    @ManyToMany
    @JoinTable(
            name = "end_user_task",
            joinColumns = @JoinColumn(name = "end_user_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    private List<Task> tasks;

    @Override
    public String toString() {
        return "EndUser{" +
                "id=" + id +
                ", task=" + tasks +
                '}';
    }
}
