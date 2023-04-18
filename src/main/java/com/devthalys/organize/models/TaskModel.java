package com.devthalys.organize.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "task")
public class TaskModel implements Serializable {
    private static final long SerialVersionUID = 1L;

    @Id
    private String id;

    private String name;

    private LocalDateTime date;

    private String taskStatus;


}
