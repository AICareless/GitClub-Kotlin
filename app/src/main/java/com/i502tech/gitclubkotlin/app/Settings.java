package com.i502tech.gitclubkotlin.app;

import com.i502tech.gitclubkotlin.model.bean.User;
import com.thejoyrun.aptpreferences.AptPreferences;

/**
 * description 持久化保存的数据
 * created by jerry on 2019/4/16.
 */
@AptPreferences
public class Settings {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
