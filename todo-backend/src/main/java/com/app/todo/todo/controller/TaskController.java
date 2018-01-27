package com.app.todo.todo.controller;

import com.app.todo.todo.controller.request.TaskRequest;
import com.app.todo.todo.repository.TODORepository;
import com.app.todo.todo.repository.entity.TaskEntity;
import com.app.todo.todo.util.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;

import static com.app.todo.todo.util.TaskStatus.COMPLETED;
import static com.app.todo.todo.util.TaskStatus.IN_PROGRESS;
import static com.app.todo.todo.util.TaskStatus.NEW;

@RestController
@RequestMapping(value = "/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    @Autowired
    private TODORepository taskRepo;

    @RequestMapping(method = RequestMethod.POST, value = "/addTask")
    public void addTask(@RequestBody TaskRequest task) {
         System.out.println(task);
        taskRepo.save(getTaskEntity(task));
    }

    @RequestMapping(value = "/getTaskByName/{name}")
    public TaskEntity getTaskByName(@PathVariable String name) {
        return taskRepo.findByName(name);
    }

    @RequestMapping(value = "/getTaskByStatus/{status}")
    public Iterable<TaskEntity> getTaskByStatus(@PathVariable String status) {
        return taskRepo.findByStatus(status);
    }

    @RequestMapping(value = "/getAllTasks")
    public Iterable<TaskEntity> getAllTasks() {
        return taskRepo.findAll();
    }

    @RequestMapping(value = "/updateTaskStatus", method = RequestMethod.POST)
    public void updateTaskStatus(@RequestBody TaskRequest task) {
        TaskEntity taskEntity = taskRepo.findByName(task.getName());
        taskEntity.setStatus(TaskStatus.valueOf(task.getStatus()));
        taskRepo.save(taskEntity);
    }

    @RequestMapping(value = "/deleteTask/{name}", method = RequestMethod.GET)
    public void deleteTask(@PathVariable String name) {
        taskRepo.deleteByName(name);
    }

    @RequestMapping(value = "/deleteAllTasks", method = RequestMethod.DELETE)
    public void deleteAll() {
        taskRepo.deleteAll();
    }

    private TaskEntity getTaskEntity(TaskRequest taskRequest) {
        final TaskEntity taskEntity = new TaskEntity();
        taskEntity.setName(taskRequest.getName());
        taskEntity.setDescription(taskRequest.getDescription());
        taskEntity.setStatus(TaskStatus.valueOf(taskRequest.getStatus()));
        return taskEntity;
    }

    @RequestMapping(value = "/updateTaskDesc")
    public void updateTaskDescription() {
    }

    void addBulkTasks(Iterable<TaskEntity> entities) {
        taskRepo.save(entities);
    }

    @PostConstruct
    public void loadTestDataOnStart() {
        String[] names = {"Morning Act", "Seminar", "Lunch", "GYM"};
        String[] desc = {"Morning wake up at 06:30 AM and meditate with HeadSpace!", "semi seminar", "with chicken and panee", "bench presses"};
        TaskStatus[] status = {COMPLETED, COMPLETED, NEW, NEW};
        List<TaskEntity> list = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            TaskEntity taskRequest = new TaskEntity();
            taskRequest.setName(names[i]);
            taskRequest.setDescription(desc[i]);
            taskRequest.setStatus(status[i]);
            list.add(taskRequest);
        }
        addBulkTasks(list);
    }
}