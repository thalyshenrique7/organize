package com.devthalys.organize.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user")
public class UserModel {

    @Id
    @JsonIgnore
    private String id;

    private String name;

    private String email;

    private String cpf;

    private String address;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private String login;

    private String password;

}
