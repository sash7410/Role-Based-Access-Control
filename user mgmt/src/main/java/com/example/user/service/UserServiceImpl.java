package com.example.user.service;

import com.example.user.domain.Role;
import com.example.user.domain.User;
import com.example.user.repository.RoleRepository;
import com.example.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;



@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public User createUserWithRoles(User user, Set<Long> roleIds) {
        Set<Role> roles = new HashSet<>(roleRepository.findAllById(roleIds));
        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Transactional
    public Optional<User> updateUserWithRoles(Long userId, User newData, Set<Long> roleIds) {
        return userRepository.findById(userId).map(user -> {
            user.setUsername(newData.getUsername());
            user.setPassword(newData.getPassword()); // Assume password is already hashed
            Set<Role> roles = new HashSet<>(roleRepository.findAllById(roleIds));
            user.setRoles(roles);
            return userRepository.save(user);
        });
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}