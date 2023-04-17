package com.devthalys.organize.repositories;

import com.devthalys.organize.dtos.UserDto;
import com.devthalys.organize.models.UserModel;
import org.apache.catalina.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserModel, String> {

    boolean existsByCpf(String cpf);
    UserModel findByCpf(String cpf);
    void deleteByCpf(String cpf);
}
