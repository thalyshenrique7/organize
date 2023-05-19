package com.devthalys.organize.models;

import com.devthalys.organize.interfaces.TaskObserver;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskObservable {

    List<TaskObserver> observers = new ArrayList<>();

    public void addObserver(TaskObserver taskObserver){
        observers.add(taskObserver);
    }

    public void removeObserver(TaskObserver taskObserver){
        observers.remove(taskObserver);
    }

    public TaskModel notifyTaskChange(TaskModel task){
        for(TaskObserver taskObserver : observers){
            taskObserver.onTaskChange(task);
        }
        return task;
    }
}
