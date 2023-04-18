package com.devthalys.organize.rest.controllers.exceptionHandler;

import com.devthalys.organize.exception.TaskNotFoundException;
import com.devthalys.organize.exception.UserAlreadyExistsException;
import com.devthalys.organize.exception.UserNotFoundException;
import com.devthalys.organize.rest.ApiErrors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(BAD_REQUEST)
    public ApiErrors handleUserNotFoundException(UserNotFoundException e){
        String errorMessage = e.getMessage();
        return new ApiErrors(errorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ApiErrors handleMethodNotValidException(MethodArgumentNotValidException e){
        List<String> errors = e.getBindingResult().getAllErrors()
                .stream()
                .map( error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        return new ApiErrors(errors);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(BAD_REQUEST)
    public ApiErrors handleResponseStatusException(UserAlreadyExistsException e){
        return new ApiErrors(e.getMessage());
    }

    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(BAD_REQUEST)
    public ApiErrors handleFieldNullException(TaskNotFoundException e){
        return new ApiErrors(e.getMessage());
    }
}
