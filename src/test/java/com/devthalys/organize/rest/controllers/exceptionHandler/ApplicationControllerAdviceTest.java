package com.devthalys.organize.rest.controllers.exceptionHandler;

import com.devthalys.organize.exception.UserNotFoundException;
import com.devthalys.organize.rest.ApiErrors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.openMocks;

class ApplicationControllerAdviceTest {

    @InjectMocks
    private ApplicationControllerAdvice applicationControllerAdvice;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void handleUserNotFoundException() {
        ApiErrors response = applicationControllerAdvice
                .handleUserNotFoundException(
                        new UserNotFoundException("User not found."));

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, HttpStatus.resolve(404));
        assertEquals(ApiErrors.class, response.getClass());
    }
}