package com.rest.webservices.restfulwebservices.user;

import com.rest.webservices.restfulwebservices.exception.UserNotFountException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    private UserDaoService service;

    public UserResource(UserDaoService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable int id) {

        User user = service.findOne(id);

        if(user== null) {
            throw new UserNotFountException("id: " + id);
        }
        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();    //returns status 201
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable int id) {
        service.deleteUserById(id);
    }
}
