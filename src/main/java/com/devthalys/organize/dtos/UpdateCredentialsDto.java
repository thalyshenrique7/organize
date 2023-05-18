package com.devthalys.organize.dtos;

import com.devthalys.organize.enums.TaskStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateCredentialsDto {

    private String taskName;

    private String description;

    private LocalDateTime lastUpdateDate;

    private TaskStatus status;
}
