package com.app.todo.todo.repository.entity;

import com.app.todo.todo.util.TaskStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
public class TaskEntity implements Serializable {

    private String id = UUID.randomUUID().toString();

    @Id
    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Temporal(TemporalType.DATE)
    private Date createdTime = new Date();

    public Date getCreatedTime() {
        return createdTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return "TaskEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", createdTime=" + createdTime +
                '}';
    }
}