package com.example.user.service;

import com.example.user.domain.Role;
import com.example.user.domain.User;
import com.example.user.repository.RoleRepository;
import com.example.user.repository.UserRepository;
import com.example.user.web.dto.requestDto.UserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public User createUser(UserRequestDTO userRequestDTO) {
        Set<Role> roles = new HashSet<>(roleRepository.findAllById(userRequestDTO.getRoles().stream().map(Role::getRoleId).collect(Collectors.toSet())));
        User user = new User();
        user.setUsername(userRequestDTO.getUsername());
        user.setPassword(userRequestDTO.getPassword()); // Assume password is already hashed
        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(Long userId, UserRequestDTO userRequestDTO) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Set<Role> roles = new HashSet<>(roleRepository.findAllById(userRequestDTO.getRoles().stream().map(Role::getRoleId).collect(Collectors.toSet())));
        user.setUsername(userRequestDTO.getUsername());
        user.setPassword(userRequestDTO.getPassword()); // Assume password is already hashed
        user.setRoles(roles);
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public User deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        userRepository.deleteById(userId);
        return user;
    }
}