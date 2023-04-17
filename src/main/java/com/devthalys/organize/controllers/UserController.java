package com.devthalys.organize.controllers;

import com.devthalys.organize.dtos.UserDto;
import com.devthalys.organize.exception.UserAlreadyExistException;
import com.devthalys.organize.exception.UserNotFoundException;
import com.devthalys.organize.models.UserModel;
import com.devthalys.organize.services.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
        return userService.findAll();
    }

    @GetMapping("{cpf}")
    public UserModel findByCpf(@PathVariable String cpf){
        return userService.findByCpf(cpf);
    }

    @Transactional
    @PostMapping
    @ResponseStatus(CREATED)
    public UserModel save(@RequestBody @Valid UserDto user){
        if(userService.existsByCpf(user.getCpf())){
            throw new UserAlreadyExistException("User already exists.");
        }

        var newUser = new UserModel();
        BeanUtils.copyProperties(user, newUser);
        return userService.save(newUser);
    }

    @Transactional
    @DeleteMapping("{cpf}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable String cpf){
        userService.deleteByCpf(cpf);
    }

    @PutMapping("{cpf}")
    @ResponseStatus(NO_CONTENT)
    public void update(@PathVariable String cpf, @RequestBody @Valid UserDto user){
        if(userService.existsByCpf(cpf)){
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
