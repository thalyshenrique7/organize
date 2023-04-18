package com.devthalys.organize.services.impl;

import com.devthalys.organize.exception.TaskNotFoundException;
import com.devthalys.organize.models.TaskModel;
import com.devthalys.organize.repositories.TaskRepository;
import com.devthalys.organize.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<TaskModel> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Optional<TaskModel> findById(String id) {
        return taskRepository.findById(id);
    }

    @Override
    public TaskModel save(TaskModel task) {
        return taskRepository.save(task);
    }

    @Override
    public void deleteById(String id) {
        taskRepository.deleteById(id);
    }
}
