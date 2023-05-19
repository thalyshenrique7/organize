package com.devthalys.organize.configs;

import com.devthalys.organize.models.TaskChangeObserver;
import com.devthalys.organize.models.TaskObservable;
import com.devthalys.organize.models.UserChangeObserver;
import com.devthalys.organize.models.UserObservable;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObserverConfig {

    private final UserObservable userObservable;
    private final TaskObservable taskObservable;
    private final UserChangeObserver userChangeObserver;
    private final TaskChangeObserver taskChangeObserver;

    public ObserverConfig(UserObservable userObservable, TaskObservable taskObservable, UserChangeObserver userChangeObserver, TaskChangeObserver taskChangeObserver) {
        this.userObservable = userObservable;
        this.taskObservable = taskObservable;
        this.userChangeObserver = userChangeObserver;
        this.taskChangeObserver = taskChangeObserver;

        userObservable.addObserver(userChangeObserver);
        taskObservable.addObserver(taskChangeObserver);
    }
}
