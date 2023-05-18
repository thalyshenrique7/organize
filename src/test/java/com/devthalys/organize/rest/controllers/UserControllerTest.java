package com.devthalys.organize.rest.controllers;

import com.devthalys.organize.configs.SecurityConfig;
import com.devthalys.organize.dtos.UserDto;
import com.devthalys.organize.models.UserModel;
import com.devthalys.organize.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class UserControllerTest {

    @InjectMocks
    private UserController controller;
    @Mock
    private UserServiceImpl service = new UserServiceImpl();
    private final UserModel user = new UserModel();
    private final UserDto userDto = new UserDto();
    private final SecurityConfig securityConfig = new SecurityConfig();

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

        ResponseEntity<List<UserModel>> response = controller.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserModel.class, response.getBody().get(0).getClass());

        assertEquals(user.getId(), response.getBody().get(0).getId());
        assertEquals(user.getName(), response.getBody().get(0).getName());
        assertEquals(user.getEmail(), response.getBody().get(0).getEmail());
        assertEquals(user.getAddress(), response.getBody().get(0).getAddress());
        assertEquals(user.getLogin(), response.getBody().get(0).getLogin());
        assertEquals(user.getPassword(), response.getBody().get(0).getPassword());
        assertEquals(user.isUserCreated(), response.getBody().get(0).isUserCreated());
    }

    @Test
    void whenFindByCpfThenReturnSuccess() {
        when(service.existsByCpf(anyString())).thenReturn(true);
        when(service.findByCpf(anyString())).thenReturn(user);

        ResponseEntity<UserModel> response = controller.findByCpf(user.getCpf());
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserModel.class, response.getBody().getClass());

        assertEquals(user.getId(), response.getBody().getId());
        assertEquals(user.getName(), response.getBody().getName());
        assertEquals(user.getEmail(), response.getBody().getEmail());
        assertEquals(user.getAddress(), response.getBody().getAddress());
        assertEquals(user.getLogin(), response.getBody().getLogin());
        assertEquals(user.getPassword(), response.getBody().getPassword());
        assertEquals(user.isUserCreated(), response.getBody().isUserCreated());
    }

    // Test with error, verifying

//    @Test
//    void whenSaveThenReturnSuccess() {
//        when(service.save(any())).thenReturn(user);
//        when(securityConfig.passwordEncoder()).thenReturn(new BCryptPasswordEncoder());
//
//        ResponseEntity<UserModel> response = controller.save(userDto);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        assertEquals(ResponseEntity.class, response.getClass());
//    }

    @Test
    void whenDeleteThenReturnSuccess() {
        doNothing().when(service).deleteByCpf(anyString());

        ResponseEntity<Object> response = controller.delete(user.getCpf());

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        verify(service, times(1)).deleteByCpf(anyString());
    }


    // Test being implemented
    @Test
    void whenUpdateThenReturnSuccess() {}
}