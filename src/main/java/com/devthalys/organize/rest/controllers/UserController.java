package com.devthalys.organize.rest.controllers;

import com.devthalys.organize.dtos.UserDto;
import com.devthalys.organize.exception.UserAlreadyExistsException;
import com.devthalys.organize.exception.UserNotFoundException;
import com.devthalys.organize.models.UserModel;
import com.devthalys.organize.services.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public List<UserModel> findAll(){
        if(userService.findAll() == null){
            throw new UserNotFoundException("Users not found.");
        }
        return userService.findAll();
    }

    @GetMapping("{cpf}")
    public UserModel findByCpf(@PathVariable String cpf){
        if(!userService.existsByCpf(cpf)){
            throw new UserNotFoundException("User not found.");
        }

        return userService.findByCpf(cpf);
    }

    @Transactional
    @PostMapping
    @ResponseStatus(CREATED)
    public UserModel save(@RequestBody @Valid UserDto user){
        if(userService.existsByCpf(user.getCpf())){
            throw new UserAlreadyExistsException("Conflict: User already exists.");
        }

        var newUser = new UserModel();
        BeanUtils.copyProperties(user, newUser);
        return userService.save(newUser);
    }

    @Transactional
    @DeleteMapping("{cpf}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable String cpf){
        if(!userService.existsByCpf(cpf)){
            throw new UserNotFoundException("User not found.");
        }
        userService.deleteByCpf(cpf);
    }

    @PutMapping("{cpf}")
    @ResponseStatus(NO_CONTENT)
    public void update(@PathVariable String cpf, @RequestBody @Valid UserDto user) {
        if(!userService.existsByCpf(cpf)){
            throw new UserNotFoundException("User not found.");
        }

        var updateUser = new UserModel();
        BeanUtils.copyProperties(user, updateUser);
        updateUser.setName(user.getName());
        updateUser.setEmail(user.getEmail());
        updateUser.setCpf(user.getCpf());
        updateUser.setAddress(user.getAddress());
        updateUser.setDate(user.getDate());
        updateUser.setLogin(user.getLogin());
        updateUser.setPassword(user.getPassword());

        userService.save(updateUser);
    }
}
