package com.devthalys.organize.dtos;

import com.devthalys.organize.enums.TaskStatus;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class TaskDto implements Serializable {
    private static final long SerialVersionUID = 1L;

    @NotBlank(message = "{required.field.user-task}")
    private String user;

    @NotBlank(message = "{required.field.name-task}")
    private String taskName;

    @NotBlank(message = "{required.field.description-task}")
    private String description;

    private LocalDateTime creationDate;

    @NotNull(message = "{required.field.status-task}")
    private TaskStatus status;
}
