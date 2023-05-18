package com.devthalys.organize.enums;

import lombok.Getter;

@Getter
public enum TaskStatus {

    IN_PROGRESS("In Progress"),
    COMPLETED("Completed"),
    PENDING("Pending");

    private final String value;

    TaskStatus(String value) {
        this.value = value;
    }

    public static TaskStatus fromValue(String value){
        for(TaskStatus statusEnum : TaskStatus.values()){
            if(statusEnum.getValue().equalsIgnoreCase(value)){
                return statusEnum;
            }
        }
        throw new IllegalArgumentException("Invalid TaskStatus value: " + value);
    }
}
