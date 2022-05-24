package com.web.lab4.repository;

import com.web.lab4.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findUserByName(String name);
}
