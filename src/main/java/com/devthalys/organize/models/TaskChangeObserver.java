package com.devthalys.organize.models;

import com.devthalys.organize.interfaces.TaskObserver;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TaskChangeObserver implements TaskObserver {

    @Override
    public void onTaskChange(TaskModel task) {
        System.out.println("Task Observer - Task changed: " + task.getId() + "\n" + task.getUser() + "\n" + LocalDateTime.now());
    }
}
