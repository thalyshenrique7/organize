package com.devthalys.organize.services.impl;

import com.devthalys.organize.dtos.UserDto;
import com.devthalys.organize.exception.UserNotFoundException;
import com.devthalys.organize.models.UserModel;
import com.devthalys.organize.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.BeanUtils;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl service;
    @Mock
    private UserRepository repository;
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
    void whenFindAllThenReturnAListOfUsers() {
        when(repository.findAll()).thenReturn(List.of(user));

        List<UserModel> response = service.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(UserModel.class, response.get(0).getClass());

        assertEquals(user.getId(), response.get(0).getId());
        assertEquals(user.getName(), response.get(0).getName());
        assertEquals(user.getEmail(), response.get(0).getEmail());
        assertEquals(user.getCpf(), response.get(0).getCpf());
    }

    @Test
    void whenSaveUserThenReturnSuccess(){
        when(repository.save(any())).thenReturn(user);

        BeanUtils.copyProperties(userDto, user);
        UserModel response = service.save(user);

        assertNotNull(response);
        assertEquals(UserModel.class, response.getClass());
        assertEquals(user.getId(), response.getId());
        assertEquals(user.getName(), response.getName());
        assertEquals(user.getEmail(), response.getEmail());
        assertEquals(user.getCpf(), response.getCpf());
    }

    // Error
//    @Test
//    void whenCreateUserSameCpfThenReturnException(){
//        when(repository.save(any())).thenReturn(user);
//
//        try {
//            service.save(user);
//        } catch (Exception e){
//            assertEquals(UserAlreadyExistsException.class, e.getClass());
//            assertEquals("Conflict: User already exists.", e.getMessage());
//        }
//    }

    @Test
    void existsByCpf() {
        when(repository.findByCpf(anyString())).thenReturn(user);

        UserModel response = service.findByCpf(user.getCpf());
        assertNotNull(response);
        assertEquals("35897035040", response.getCpf());

    }

    @Test
    void whenFindByCpfReturnAnUserInstance() {
        when(repository.findByCpf(anyString())).thenReturn(user);

        UserModel response = service.findByCpf(user.getCpf());

        assertNotNull(response);
        assertEquals(UserModel.class, response.getClass());
        assertEquals(user.getId(), response.getId());
        assertEquals(user.getName(), response.getName());
        assertEquals(user.getEmail(), response.getEmail());
        assertEquals(user.getCpf(), response.getCpf());
    }

    @Test
    void whenFindByCpfThenReturnAnUserNotFoundException(){
        when(repository.findByCpf(anyString())).thenThrow(new UserNotFoundException("User not found."));

        try {
            service.findByCpf(user.getCpf());
        } catch (Exception e){
            assertEquals(UserNotFoundException.class, e.getClass());
            assertEquals("User not found.", e.getMessage());
        }
    }

    @Test
    void findByLogin() {
        when(repository.findByLogin(anyString())).thenReturn(user);

        UserModel response = service.findByLogin(user.getLogin());
        assertNotNull(response);
        assertEquals("th", response.getLogin());
    }

    @Test
    void whenDeleteByCpfThenReturnSuccess() {
        when(repository.findByCpf(anyString())).thenReturn(user);
        doNothing().when(repository).deleteByCpf(anyString());
        service.deleteByCpf(user.getCpf());
        verify(repository, times(1)).deleteByCpf(anyString());
    }

    @Test
    void whenDeleteByCpfThenReturnException() {
        when(repository.findByCpf(anyString())).thenThrow(new UserNotFoundException("User not found."));

        try {
            service.findByCpf(user.getCpf());
            service.deleteByCpf(user.getCpf());
        } catch (Exception e){
            assertEquals("User not found.", e.getMessage());
        }
    }
}