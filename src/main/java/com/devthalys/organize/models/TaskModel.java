package com.devthalys.organize.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "task")
public class TaskModel implements Serializable {
    private static final long SerialVersionUID = 1L;

    @Id
    private String id;

    private String nameTask;

    private String description;

    private LocalDateTime dateCreation;

    @JsonIgnore
    private LocalDateTime updateDate;

    private String taskStatus;

    @DBRef
    private UserModel user;

}
