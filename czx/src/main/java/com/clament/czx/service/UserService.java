package com.clament.czx.service;

import com.clament.czx.DataEntity.User;

public interface UserService {

    User checkUser(String username, String password);
}
