package com.example.attendance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class SecurityMiddleware {

    @Autowired
    private UserRoleService userRoleService;

    public boolean hasPermission(Long userId, String requiredPermission) {
        Set<String> permissions = userRoleService.getPermissionsForUser(userId);
        System.out.println(permissions);
        return permissions.contains(requiredPermission);
    }
}