package com.devthalys.organize.configs;

import com.devthalys.organize.models.UserChangeObserver;
import com.devthalys.organize.models.UserObservable;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObserverConfig {

    private final UserObservable userObservable;
    private final UserChangeObserver userChangeObserver;

    public ObserverConfig(UserObservable userObservable, UserChangeObserver userChangeObserver) {
        this.userObservable = userObservable;
        this.userChangeObserver = userChangeObserver;

        userObservable.addObserver(userChangeObserver);
    }
}
