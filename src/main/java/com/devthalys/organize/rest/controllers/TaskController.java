package com.devthalys.organize.rest.controllers;

import com.devthalys.organize.dtos.TaskDto;
import com.devthalys.organize.dtos.UpdateCredentialsDto;
import com.devthalys.organize.exception.TaskNotFoundException;
import com.devthalys.organize.models.TaskModel;
import com.devthalys.organize.services.impl.TaskServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
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

    @PostMapping(value = "/save")
    @ResponseStatus(CREATED)
    public TaskModel save(@RequestBody @Valid TaskDto task){
        return service.save(task);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable String id){
        service.deleteById(id);
    }

    @PutMapping(value = "/update/{id}")
    @ResponseStatus(NO_CONTENT)
    public void update(@PathVariable String id, @RequestBody @Valid UpdateCredentialsDto updateCredentialsDto){
        TaskModel task = service.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));

        var newTask = new TaskModel();
        BeanUtils.copyProperties(task, newTask);

        newTask.setNameTask(updateCredentialsDto.getNameTask());
        newTask.setDescription(updateCredentialsDto.getDescription());
        newTask.setUpdateDate(LocalDateTime.now());
        newTask.setTaskStatus(updateCredentialsDto.getTaskStatus());

        service.update(newTask);

    }
}
