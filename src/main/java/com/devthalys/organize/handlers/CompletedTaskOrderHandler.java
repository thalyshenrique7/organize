package com.devthalys.organize.handlers;

import com.devthalys.organize.enums.TaskStatus;
import com.devthalys.organize.interfaces.TaskOrderHandler;
import com.devthalys.organize.models.TaskModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CompletedTaskOrderHandler implements TaskOrderHandler {

    private TaskOrderHandler nextHandler;

    @Override
    public void setNext(TaskOrderHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public List<TaskModel> handle(List<TaskModel> tasks) {
        List<TaskModel> completedTasks = tasks
                .stream()
                .filter(task -> task.getStatus().equals(TaskStatus.COMPLETED))
                .collect(Collectors.toList());

        if(nextHandler != null){
            return nextHandler.handle(completedTasks);
        }

        return completedTasks;
    }
}
