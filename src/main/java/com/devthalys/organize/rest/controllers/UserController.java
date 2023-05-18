package com.devthalys.organize.rest.controllers;

import com.devthalys.organize.dtos.UserDto;
import com.devthalys.organize.exception.UserAlreadyExistsException;
import com.devthalys.organize.exception.UserNotFoundException;
import com.devthalys.organize.models.UserModel;
import com.devthalys.organize.services.impl.UserServiceImpl;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

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
    public ResponseEntity<List<UserModel>> findAll(){
        List<UserModel> findAll = userService.findAll();
        if(findAll == null){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserNotFoundException("Users list not found."));
        }
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @GetMapping("{cpf}")
    @ApiOperation(value = "Get details of specific user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User found"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public ResponseEntity<UserModel> findByCpf(@PathVariable @ApiParam("User Cpf") String cpf){
        if(!userService.existsByCpf(cpf)) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserNotFoundException("User not found."));
        }
        return ResponseEntity.status(HttpStatus.OK).body(userService.findByCpf(cpf));
    }

    @Transactional
    @PostMapping
    @ApiOperation(value = "Save user in data base")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User saved successful"),
            @ApiResponse(code = 404, message = "Error tried user saving")
    })
    public ResponseEntity<UserModel> save(@RequestBody @Valid UserDto user){
        if(userService.existsByCpf(user.getCpf())){
             throw new UserAlreadyExistsException("Conflict: User already exists.");
        }

        var newUser = new UserModel();
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        BeanUtils.copyProperties(user, newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(newUser));
    }

    @Transactional
    @DeleteMapping("{cpf}")
    @ApiOperation(value = "Delete user by cpf")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User deleted successful"),
            @ApiResponse(code = 404, message = "Error tried user delete")
    })
    public ResponseEntity<Object> delete(@PathVariable @ApiParam(value = "User Cpf") String cpf){
        if(!userService.existsByCpf(cpf)){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserNotFoundException("User not found."));
        }

        userService.deleteByCpf(cpf);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User deleted successful.");
    }

    @PutMapping("{cpf}")
    @ApiOperation(value = "Update user by cpf")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User updated successful"),
            @ApiResponse(code = 404, message = "Error tried user updating")
    })
    public ResponseEntity<Object> update(@PathVariable @ApiParam(value = "User Cpf") String cpf, @RequestBody @Valid UserDto user) {
        if(!userService.existsByCpf(cpf)){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserNotFoundException("User not found."));
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

        userService.deleteByCpf(user.getCpf());
        userService.save(updateUser);
        return ResponseEntity.status(NO_CONTENT).body("User updated successful");
    }
}
