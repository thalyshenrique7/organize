package com.devthalys.organize.rest.controllers;

import com.devthalys.organize.dtos.TaskDto;
import com.devthalys.organize.exception.TaskNotFoundException;
import com.devthalys.organize.models.TaskModel;
import com.devthalys.organize.services.impl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskServiceImpl service;

    @GetMapping
    public List<TaskModel> findAll(){
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    public TaskModel findById(@PathVariable String id){
        return service.findById(id)
                .map( task -> {
                    task.getId();
                         return task;
        }).orElseThrow(() -> new TaskNotFoundException("Task not found"));
    }

    @PostMapping(value = "/users")
    @ResponseStatus(CREATED)
    public TaskModel save(@RequestBody TaskDto task){
        return service.saveTaskAndUser(task);
    }

    @PostMapping(value = "/")
    public TaskModel saveTask(@RequestBody TaskModel task){
        return service.saveTask(task);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable String id){
        service.deleteById(id);
    }
}
