package com.devthalys.organize.services.impl;

import com.devthalys.organize.models.UserModel;
import com.devthalys.organize.repositories.UserRepository;
import com.devthalys.organize.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean existsByCpf(String cpf) {
        return userRepository.existsByCpf(cpf);
    }

    @Override
    public UserModel findByCpf(String cpf) {
        return userRepository.findByCpf(cpf);
    }

    @Override
    public UserModel save(UserModel user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteByCpf(String cpf) {
        userRepository.deleteByCpf(cpf);
    }
}
