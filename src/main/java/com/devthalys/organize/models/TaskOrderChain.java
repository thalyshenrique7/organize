package com.devthalys.organize.models;

import com.devthalys.organize.interfaces.TaskOrderHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskOrderChain {

    private TaskOrderHandler firstHandler;

    public void addHandler(List<TaskOrderHandler> handlers){
        if(!handlers.isEmpty()){
            firstHandler = handlers.get(0);
            for(int i = 0; i < handlers.size(); i++){
                TaskOrderHandler currentHandler = handlers.get(i);
                TaskOrderHandler nextHandler = handlers.get(i + 1);
                currentHandler.setNext(nextHandler);
            }
        }
    }

    public List<TaskModel> orderTasks(List<TaskModel> tasks){
        if(firstHandler != null){
            return firstHandler.handle(tasks);
        } else {
            return tasks;
        }
    }
}
