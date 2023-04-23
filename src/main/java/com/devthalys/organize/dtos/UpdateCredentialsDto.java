package com.devthalys.organize.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateCredentialsDto {

    private String nameTask;

    private String description;

    private LocalDateTime updateDate;

    private String taskStatus;
}
