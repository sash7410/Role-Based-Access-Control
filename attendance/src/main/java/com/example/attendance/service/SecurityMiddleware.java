package com.example.attendance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

//@Component
//public class SecurityMiddleware {
//
//    @Autowired
//    private UserRoleService userRoleService; // Service to fetch user roles and permissions
//
//    public boolean hasPermission(Long userId, String permission) {
//        Set<String> permissions = userRoleService.getPermissionsForUser(userId);
//        return permissions.contains(permission);
//    }
//}
//@Component
//public class SecurityMiddleware {
//
//    public boolean hasPermission(Long userId, String requiredPermission) {
//        // Dummy check: Allowing all actions if userId is not null
//        return userId != null;
//    }
//}
@Component
public class SecurityMiddleware {

    @Autowired
    private UserRoleService userRoleService;

    public boolean hasPermission(Long userId, String requiredPermission) {
        Set<String> permissions = userRoleService.getPermissionsForUser(userId);
        return permissions.contains(requiredPermission);
    }
}