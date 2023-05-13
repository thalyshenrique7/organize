package com.devthalys.organize.rest.controllers;

import com.devthalys.organize.models.UserModel;
import com.devthalys.organize.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class UserControllerTest {

    @InjectMocks
    private UserController controller;
    @Mock
    private UserServiceImpl service = new UserServiceImpl();
    private UserModel user = new UserModel();

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
    void whenFindAllThenReturnSuccess() {
        when(service.findAll()).thenReturn(List.of(user));

        List<UserModel> response = controller.findAll();
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, HttpStatus.resolve(204));
        assertEquals(response.size(), 1);
        assertEquals(UserModel.class, response.get(0).getClass());

        assertEquals(user.getId(), response.get(0).getId());
        assertEquals(user.getName(), response.get(0).getName());
        assertEquals(user.getEmail(), response.get(0).getEmail());
        assertEquals(user.getAddress(), response.get(0).getAddress());
        assertEquals(user.getLogin(), response.get(0).getLogin());
        assertEquals(user.getPassword(), response.get(0).getPassword());
        assertEquals(user.isUserCreated(), response.get(0).isUserCreated());
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
    void whenSaveThenReturnSuccess() {
        when(service.save(any())).thenReturn(user);

        UserModel response = service.save(user);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, HttpStatus.resolve(201));
        assertEquals(UserModel.class, response.getClass());
    }

    @Test
    void whenDeleteThenReturnSuccess() {
        doNothing().when(service).deleteByCpf(anyString());

        service.deleteByCpf(user.getCpf());
        verify(service, times(1)).deleteByCpf(anyString());
        assertEquals(HttpStatus.NO_CONTENT, HttpStatus.resolve(204));
    }

    @Test
    void whenUpdateThenReturnSuccess() {
    }
}