package com.devthalys.organize.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotBlank(message = "{required.field.name-user}")
    private String name;

    @Email(message = "{required.field.email-valid}")
    @NotNull(message = "{required.field.email-user}")
    private String email;

    @CPF(message = "{required.field.cpf-valid}")
    @NotBlank(message = "{required.field.cpf-user}")
    private String cpf;

    @NotBlank(message = "{required.field.address-user}")
    private String address;

    @NotNull(message = "{required.field.date-user}")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @NotBlank(message = "{required.field.login-user}")
    private String login;

    @NotBlank(message = "{required.field.password-user}")
    private String password;
}
