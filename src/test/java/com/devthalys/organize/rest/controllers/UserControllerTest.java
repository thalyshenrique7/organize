package com.devthalys.organize.rest.controllers;

import com.devthalys.organize.dtos.UserDto;
import com.devthalys.organize.models.UserModel;
import com.devthalys.organize.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class UserControllerTest {

    @InjectMocks
    private UserController controller;
    @Mock
    private UserServiceImpl service = new UserServiceImpl();
    private UserModel user = new UserModel();
    private UserDto userDto = new UserDto();

    @BeforeEach
    void setUp() {
        openMocks(this);

        user.setId("89af766sef");
        user.setName("Thalys");
        user.setEmail("thalys@email.com");
        user.setCpf("35897035040");
        user.setAddress("João Pessoa");
        user.setLogin("th");
        user.setPassword("123");
        user.setUserCreated(true);
    }

    @Test
    void findAll() {
    }

    @Test
    void whenFindByCpfThenReturnSuccess() {
        when(service.existsByCpf(anyString())).thenReturn(true);
        when(service.findByCpf(anyString())).thenReturn(user);

        UserModel response = controller.findByCpf(user.getCpf());
        assertNotNull(response);
        assertEquals(user, response);
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }
}