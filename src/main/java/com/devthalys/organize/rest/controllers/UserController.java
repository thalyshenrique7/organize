package com.devthalys.organize.rest.controllers;

import com.devthalys.organize.dtos.UserDto;
import com.devthalys.organize.exception.UserAlreadyExistsException;
import com.devthalys.organize.exception.UserNotFoundException;
import com.devthalys.organize.models.UserModel;
import com.devthalys.organize.services.impl.UserServiceImpl;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/users")
@Api(value = "Task List API")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    @ApiOperation(value = "Get details of users list")
    public List<UserModel> findAll(){
        if(userService.findAll() == null){
            throw new UserNotFoundException("Users not found.");
        }
        return userService.findAll();
    }

    @GetMapping("{cpf}")
    @ApiOperation(value = "Get details of specific user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User found"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public UserModel findByCpf(@PathVariable @ApiParam("User ID") String cpf){
        if(!userService.existsByCpf(cpf)){
            throw new UserNotFoundException("User not found.");
        }

        return userService.findByCpf(cpf);
    }

    @Transactional
    @PostMapping
    @ApiOperation(value = "Save user in data base")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User saved successful"),
            @ApiResponse(code = 404, message = "Error tried user saving")
    })
    @ResponseStatus(CREATED)
    public UserModel save(@RequestBody @Valid UserDto user){
        if(userService.existsByCpf(user.getCpf())){
            throw new UserAlreadyExistsException("Conflict: User already exists.");
        }

        var newUser = new UserModel();
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        BeanUtils.copyProperties(user, newUser);
        newUser.setUserCreated(true);
        return userService.save(newUser);
    }

    @Transactional
    @DeleteMapping("{cpf}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation(value = "Delete user by cpf")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User deleted successful"),
            @ApiResponse(code = 404, message = "Error tried user delete")
    })
    public void delete(@PathVariable @ApiParam(value = "User Cpf") String cpf){
        if(!userService.existsByCpf(cpf)){
            throw new UserNotFoundException("User not found.");
        }
        userService.deleteByCpf(cpf);
    }

    @PutMapping("{cpf}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation(value = "Update user by cpf")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User updated successful"),
            @ApiResponse(code = 404, message = "Error tried user updating")
    })
    public void update(@PathVariable @ApiParam(value = "User Cpf") String cpf, @RequestBody @Valid UserDto user) {
        if(!userService.existsByCpf(cpf)){
            throw new UserNotFoundException("User not found.");
        }

        var updateUser = new UserModel();
        BeanUtils.copyProperties(user, updateUser);
        updateUser.setName(user.getName());
        updateUser.setEmail(user.getEmail());
        updateUser.setCpf(user.getCpf());
        updateUser.setAddress(user.getAddress());
        updateUser.setDate(LocalDateTime.now());
        updateUser.setLogin(user.getLogin());
        updateUser.setPassword(user.getPassword());

        userService.save(updateUser);
    }
}
