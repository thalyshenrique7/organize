package com.devthalys.organize.models;

import com.devthalys.organize.interfaces.UserObserver;
import org.springframework.stereotype.Component;

@Component
public class UserChangeObserver implements UserObserver {

    @Override
    public void onUserChange(UserModel user) {
        System.out.println("User saved: " + user.getName());
    }
}
