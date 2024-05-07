package com.example.role.web.controller;

import com.example.role.domain.Role;
import com.example.role.domain.Permission;

import com.example.role.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/roles-permissions")
public class RolePermissionController {

    @Autowired
    private RolePermissionService rolePermissionService;

    // Role Management
    @PostMapping("/roles")
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        Role createdRole = rolePermissionService.createRole(role);
        return ResponseEntity.ok(createdRole);
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        Optional<Role> role = rolePermissionService.getRoleById(id);
        return role.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = rolePermissionService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    @PutMapping("/roles/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody Role role) {
        Optional<Role> updatedRole = rolePermissionService.updateRole(id, role);
        return updatedRole.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/roles/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        rolePermissionService.deleteRole(id);
        return ResponseEntity.ok().build();
    }

    // Permission Management
    @PostMapping("/permissions")
    public ResponseEntity<Permission> createPermission(@RequestBody Permission permission) {
        Permission createdPermission = rolePermissionService.createPermission(permission);
        return ResponseEntity.ok(createdPermission);
    }

    @GetMapping("/permissions/{id}")
    public ResponseEntity<Permission> getPermissionById(@PathVariable Long id) {
        Optional<Permission> permission = rolePermissionService.getPermissionById(id);
        return permission.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/permissions")
    public ResponseEntity<List<Permission>> getAllPermissions() {
        List<Permission> permissions = rolePermissionService.getAllPermissions();
        return ResponseEntity.ok(permissions);
    }

    @PutMapping("/permissions/{id}")
    public ResponseEntity<Permission> updatePermission(@PathVariable Long id, @RequestBody Permission permission) {
        Optional<Permission> updatedPermission = rolePermissionService.updatePermission(id, permission);
        return updatedPermission.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/permissions/{id}")
    public ResponseEntity<Void> deletePermission(@PathVariable Long id) {
        rolePermissionService.deletePermission(id);
        return ResponseEntity.ok().build();
    }
}