package com.example.user.web.controller;

import com.example.user.domain.User;
import com.example.user.service.UserServiceImpl;
import com.example.user.web.dto.requestDto.UserRequestDTO;
import com.example.user.web.dto.responseDto.ResponseDto;
import com.example.user.web.exception.ExceptionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<ResponseDto> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        ResponseDto response = new ResponseDto();
        try {
            User createdUser = userService.createUser(userRequestDTO);
            response.setSuccess(createdUser);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error, try again", e.getMessage());
            response.setError(exceptionResponse);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<ResponseDto> loginUser(@RequestBody UserRequestDTO userRequestDTO) {
        ResponseDto response = new ResponseDto();
        try {
            Optional<User> user = userService.loginUser(userRequestDTO.getUsername(), userRequestDTO.getPassword());
            if (user.isPresent()) {
                response.setSuccess(user.get());
                return ResponseEntity.ok(response);
            } else {
                response.setError(new ExceptionResponse(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "Login failed", "Invalid credentials"));
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        } catch (Exception e) {
            response.setError(new ExceptionResponse(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Login failed", e.getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    @GetMapping("/{userId}")
    public ResponseEntity<ResponseDto> getUserById(@PathVariable Long userId) {
        ResponseDto response = new ResponseDto();
        try {
            User user = userService.getUserById(userId).orElse(null);
            if (user != null) {
                response.setSuccess(user);
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error, try again", e.getMessage());
            response.setError(exceptionResponse);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseDto> getAllUsers() {
        ResponseDto response = new ResponseDto();
        try {
            List<User> users = userService.getAllUsers();
            response.setSuccess(users);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error, try again", e.getMessage());
            response.setError(exceptionResponse);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ResponseDto> updateUser(@PathVariable Long userId, @RequestBody UserRequestDTO userRequestDTO) {
        ResponseDto response = new ResponseDto();
        try {
            User updatedUser = userService.updateUser(userId, userRequestDTO);
            response.setSuccess(updatedUser);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error, try again", e.getMessage());
            response.setError(exceptionResponse);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ResponseDto> deleteUser(@PathVariable Long userId) {
        ResponseDto response = new ResponseDto();
        try {
            response.setSuccess(userService.deleteUser(userId));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error, try again", e.getMessage());
            response.setError(exceptionResponse);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}