package com.myTodo.data.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.myTodo.data.model.DueDate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;
import static java.time.LocalDateTime.now;

@Entity
@Setter
@Getter
public class Task {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String message;
    private Long userId;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime created;
    @OneToOne
    @JoinColumn(name = "due_date_id")
    private DueDate dueDate;

    @PrePersist
    public void setCreated(){
        this.created = now();
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", userId=" + userId +
                ", created=" + created +
                ", dueDate=" + dueDate +
                '}';
    }
}
