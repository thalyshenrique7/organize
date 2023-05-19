package com.devthalys.organize.services.impl;

import com.devthalys.organize.models.TaskModel;
import com.devthalys.organize.models.UserModel;
import com.devthalys.organize.models.UserObservable;
import com.devthalys.organize.repositories.TaskRepository;
import com.devthalys.organize.repositories.UserRepository;
import com.devthalys.organize.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserObservable userObservable;

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
    public UserModel findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public UserModel save(UserModel user) {
        user.setUserCreated(true);
        userRepository.save(user);
        userObservable.notifyUserChange(user);
        return user;
    }

    @Override
    public void deleteByCpf(String cpf) {
        UserModel findUser = userRepository.findByCpf(cpf);

        if(findUser != null){
            List<TaskModel> userTasks = taskRepository.findByUser(findUser);
            for(TaskModel task : userTasks){
                taskRepository.deleteById(task.getId());
            }
        }

        userRepository.deleteByCpf(cpf);
        userObservable.notifyUserChange(findUser);
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepository.findByLogin(username);

        if(user == null){
            throw new UsernameNotFoundException("User not found.");
        }

        String[] role = user.isUserCreated() ?
                new String[]{"USER"} : new String[]{" "};

        return User
                .builder()
                .username(user.getLogin())
                .password(user.getPassword())
                .roles(role)
                .build();
    }
}
