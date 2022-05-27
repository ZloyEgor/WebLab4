package com.web.lab4.controller;

import com.web.lab4.entity.User;
import com.web.lab4.security.UserService;
import org.apache.commons.collections4.IterableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("account")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @CrossOrigin
    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody User newUser) {
        if (userService.findByUsername(newUser.getName()) != null) {
            logger.error("Username already exist: " + newUser.getName());
            return new ResponseEntity<>(
                    new RuntimeException("User with username " + newUser.getName() + "already exist"),
                    HttpStatus.CONFLICT);
        }

        logger.info("User registered " + newUser.getName());

        return new ResponseEntity<>(userService.save(newUser), HttpStatus.CREATED);
    }

    @CrossOrigin
    @RequestMapping("/login")
    public Principal user(Principal principal) {
        logger.info("User logged "+principal);
        return principal;
    }
}
