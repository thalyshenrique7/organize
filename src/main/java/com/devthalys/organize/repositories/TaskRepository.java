package com.devthalys.organize.repositories;

import com.devthalys.organize.enums.TaskStatus;
import com.devthalys.organize.models.TaskModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TaskRepository extends MongoRepository<TaskModel, String> {

    List<TaskModel> findByStatus(TaskStatus status);
}
