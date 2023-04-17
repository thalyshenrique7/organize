package com.devthalys.organize.services;

import com.devthalys.organize.dtos.UserDto;
import com.devthalys.organize.models.UserModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<UserModel> findAll();
    boolean existsByCpf(String cpf);
    UserModel findByCpf(String cpf);
    void deleteByCpf(String cpf);
    UserModel save(UserModel user);

}
