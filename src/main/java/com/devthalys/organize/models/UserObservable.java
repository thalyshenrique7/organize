package com.devthalys.organize.models;

import com.devthalys.organize.interfaces.UserObserver;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserObservable {

    private List<UserObserver> observers = new ArrayList<>();

    public void addObserver(UserObserver userObserver){
        observers.add(userObserver);
    }

    public void removeObserver(UserObserver userObserver){
        observers.remove(userObserver);
    }

    public void notifyUserChange(UserModel user){
        for(UserObserver userObserver : observers){
            userObserver.onUserChange(user);
        }
    }
}
