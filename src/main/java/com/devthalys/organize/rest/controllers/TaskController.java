package com.devthalys.organize.rest.controllers;

import com.devthalys.organize.dtos.TaskDto;
import com.devthalys.organize.dtos.UpdateCredentialsDto;
import com.devthalys.organize.models.TaskModel;
import com.devthalys.organize.services.impl.TaskServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskServiceImpl service;

    @GetMapping
    public ResponseEntity<List<TaskModel>> findAll(){
        List<TaskModel> findAll = service.findAll();
        if(findAll == null){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task List not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TaskModel> findById(@PathVariable String id){
        Optional<TaskModel> findById = service.findById(id);
        if(findById.isEmpty()){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
        }
        service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(findById.get());
    }

    @PostMapping(value = "/save")
    public ResponseEntity<TaskModel> save(@RequestBody @Valid TaskDto task){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(task));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id){
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Task deleted successful");
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody @Valid UpdateCredentialsDto updateCredentialsDto){
        Optional<TaskModel> task = service.findById(id);
            if (task.isEmpty()){
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
            }

        var newTask = new TaskModel();
        BeanUtils.copyProperties(task, newTask);

        newTask.setNameTask(updateCredentialsDto.getNameTask());
        newTask.setDescription(updateCredentialsDto.getDescription());
        newTask.setUpdateDate(LocalDateTime.now());
        newTask.setTaskStatus(updateCredentialsDto.getTaskStatus());

        service.update(newTask);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Task updated successful");
    }
}
