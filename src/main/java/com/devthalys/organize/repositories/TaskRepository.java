package com.devthalys.organize.repositories;

import com.devthalys.organize.models.TaskModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<TaskModel, String> {


}
