package com.devthalys.organize.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    private LocalDateTime date;

    @NotBlank(message = "{required.field.login-user}")
    private String login;

    @NotBlank(message = "{required.field.password-user}")
    private String password;

    private boolean userCreated;
}
