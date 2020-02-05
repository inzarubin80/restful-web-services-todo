package com.zarubin.rest.webservices.restfulwebservices.jwt;
import com.zarubin.rest.webservices.restfulwebservices.jwt.resource.JwtTokenRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@CrossOrigin(origins="https://i-todo.herokuapp.com", maxAge = 3600)
public class UserController {

    @Autowired
    UserJpaRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody JwtTokenRequest authenticationRequest) {

        logger.warn("IN SIGNUP");

        if (repository.findByUsername(authenticationRequest.getUsername()) == null) {
            User user = new User();
            user.setPassword(passwordEncoder.encode(authenticationRequest.getPassword()));
            user.setUsername(authenticationRequest.getUsername());
            user.setRole("ROLE_USER_2");
            user.setEnabled(1);
            repository.save(user);
            return new ResponseEntity<JwtTokenRequest>(authenticationRequest, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<JwtTokenRequest>(authenticationRequest, HttpStatus.BAD_REQUEST);
        }

    };
}

