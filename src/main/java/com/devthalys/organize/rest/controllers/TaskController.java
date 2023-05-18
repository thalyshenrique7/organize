package com.devthalys.organize.rest.controllers;

import com.devthalys.organize.dtos.TaskDto;
import com.devthalys.organize.dtos.UpdateCredentialsDto;
import com.devthalys.organize.enums.TaskStatus;
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
    private TaskServiceImpl taskService;

    @GetMapping
    public ResponseEntity<List<TaskModel>> findAll(){
        List<TaskModel> findAll = taskService.findAll();
        if(findAll == null){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task List not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(taskService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TaskModel> findById(@PathVariable String id){
        Optional<TaskModel> findById = taskService.findById(id);
        if(findById.isEmpty()){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
        }
        taskService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(findById.get());
    }

    @GetMapping(value = "/ordered/{order}")
    public ResponseEntity<List<TaskModel>> findTasksOrderedByStatus(@PathVariable String order){
        List<TaskModel> orderedTasks;

        if(order.equals("pending")){
            orderedTasks = taskService.findTasksOrderedByStatus(TaskStatus.PENDING);
        } else if (order.equals("in progress")){
            orderedTasks = taskService.findTasksOrderedByStatus(TaskStatus.IN_PROGRESS);
        } else if(order.equals("completed")){
            orderedTasks = taskService.findTasksOrderedByStatus(TaskStatus.COMPLETED);
        } else {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(orderedTasks);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<TaskModel> save(@RequestBody @Valid TaskDto task){
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.save(task));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id){
        taskService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Task deleted successful");
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody @Valid UpdateCredentialsDto updateCredentialsDto){
        Optional<TaskModel> task = taskService.findById(id);
            if (task.isEmpty()){
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
            }

        var newTask = new TaskModel();
        BeanUtils.copyProperties(task, newTask);

        newTask.setTaskName(updateCredentialsDto.getTaskName());
        newTask.setDescription(updateCredentialsDto.getDescription());
        newTask.setLastUpdateDate(LocalDateTime.now());
        newTask.setStatus(updateCredentialsDto.getStatus());

        taskService.update(newTask);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Task updated successful");
    }
}
