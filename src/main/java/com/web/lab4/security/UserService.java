package com.web.lab4.security;

import com.web.lab4.entity.User;

public interface UserService {
    User save(User user);

    User findByUsername(String username);
}
