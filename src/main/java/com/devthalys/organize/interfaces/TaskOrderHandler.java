package com.devthalys.organize.interfaces;

import com.devthalys.organize.models.TaskModel;

import java.util.List;

public interface TaskOrderHandler {

    void setNext(TaskOrderHandler nextHandler);
    List<TaskModel> handle(List<TaskModel> status);
}
