package com.example.user.web.controller;

import com.example.user.domain.User;
import com.example.user.domain.Role;
import com.example.user.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        Set<Long> roleIds = user.getRoles().stream().map(Role::getId).collect(Collectors.toSet());
        User createdUser = userService.createUserWithRoles(user, roleIds);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        Set<Long> roleIds = user.getRoles().stream().map(Role::getId).collect(Collectors.toSet());
        return userService.updateUserWithRoles(id, user, roleIds)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}