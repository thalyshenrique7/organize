package com.devthalys.organize.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "user")
public class UserModel {

    @Id
    private String id;

    private String name;

    private String email;

    private String cpf;

    private String address;

    private LocalDateTime date;

    private String login;

    private String password;

}
