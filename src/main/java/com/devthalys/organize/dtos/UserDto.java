package com.devthalys.organize.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String name;

    private String email;

    private String cpf;

    private String address;

    private LocalDateTime date;

    private String login;

    private String password;
}
