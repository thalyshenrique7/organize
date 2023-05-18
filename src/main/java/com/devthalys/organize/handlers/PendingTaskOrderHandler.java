package com.devthalys.organize.handlers;

import com.devthalys.organize.enums.TaskStatus;
import com.devthalys.organize.interfaces.TaskOrderHandler;
import com.devthalys.organize.models.TaskModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PendingTaskOrderHandler implements TaskOrderHandler {

    private TaskOrderHandler nextHandler;

    @Override
    public void setNext(TaskOrderHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public List<TaskModel> handle(List<TaskModel> tasks) {
        List<TaskModel> pendingTasks = tasks
                .stream()
                .filter(task -> task.getStatus().equals(TaskStatus.PENDING))
                .collect(Collectors.toList());

        if (nextHandler != null) {
            return nextHandler.handle(pendingTasks);
        }

        return pendingTasks;
    }
}
