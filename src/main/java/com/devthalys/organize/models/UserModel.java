package com.devthalys.organize.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private String id;

    private String name;

    private String email;

    private String cpf;

    private String address;

    private LocalDateTime date;

    @JsonIgnore
    private String login;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private boolean userCreated;

}
