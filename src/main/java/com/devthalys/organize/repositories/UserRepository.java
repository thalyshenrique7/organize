package com.devthalys.organize.repositories;

import com.devthalys.organize.models.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserModel, String> {

    boolean existsByCpf(String cpf);

    UserModel findByCpf(String cpf);

    UserModel findByLogin(String login);

    void deleteByCpf(String cpf);
}
