package com.devthalys.organize.services;

import com.devthalys.organize.dtos.TaskDto;
import com.devthalys.organize.models.TaskModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TaskService {

    List<TaskModel> findAll();

    Optional<TaskModel> findById(String id);

    TaskModel saveTaskAndUser(TaskDto task);

    TaskModel saveTask(TaskModel task);

    void deleteById(String id);
}
