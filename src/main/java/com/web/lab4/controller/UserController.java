package com.web.lab4.controller;

import com.web.lab4.entity.User;
import com.web.lab4.security.UserService;
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
    @PostMapping(path = "/register", produces = "application/json")
    public ResponseEntity<?> createUser(@RequestBody User newUser) {
        if (userService.findByUsername(newUser.getName()) != null) {
            String message = "User with username " + newUser.getName() + " already exists";
            logger.error(message);
            return new ResponseEntity<>(new RuntimeException(message), HttpStatus.CONFLICT);
        }

        logger.info("User registered " + newUser.getName());

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(newUser));
    }

    @CrossOrigin
    @RequestMapping("/login")
    public Principal user(Principal principal) {
        if (principal == null) {
            logger.info("Not logged in");
        } else {
            logger.info("Logged " + principal.getName());
        }
        return principal;
    }
}
