package com.devthalys.organize.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class TaskDto implements Serializable {
    private static final long SerialVersionUID = 1L;

    @NotBlank(message = "{required.field.user-task}")
    private String user;

    @NotBlank(message = "{required.field.name-task}")
    private String nameTask;

    @NotBlank(message = "{required.field.description-task}")
    private String description;

    private LocalDateTime dateCreation;

    @NotBlank(message = "{required.field.status-task}")
    private String taskStatus;
}
