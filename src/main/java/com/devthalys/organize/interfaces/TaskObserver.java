package com.devthalys.organize.interfaces;

import com.devthalys.organize.models.TaskModel;

public interface TaskObserver {

    void onTaskChange(TaskModel task);
}
