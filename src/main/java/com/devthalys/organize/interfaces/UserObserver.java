package com.devthalys.organize.interfaces;

import com.devthalys.organize.models.UserModel;

public interface UserObserver {

    void onUserChange(UserModel user);
}
