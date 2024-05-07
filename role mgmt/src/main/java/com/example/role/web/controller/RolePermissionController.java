package com.example.role.web.controller;

import com.example.role.domain.Permission;
import com.example.role.domain.Role;
import com.example.role.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/roles/{roleId}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long roleId) {
        Optional<Role> role = rolePermissionService.getRoleById(roleId);
        return role.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = rolePermissionService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    @PutMapping("/roles/{roleId}")
    public ResponseEntity<Role> updateRole(@PathVariable Long roleId, @RequestBody Role role) {
        Optional<Role> updatedRole = rolePermissionService.updateRole(roleId, role);
        return updatedRole.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/roles/{roleId}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long roleId) {
        rolePermissionService.deleteRole(roleId);
        return ResponseEntity.ok().build();
    }

    // Permission Management
    @PostMapping("/permissions")
    public ResponseEntity<Permission> createPermission(@RequestBody Permission permission) {
        Permission createdPermission = rolePermissionService.createPermission(permission);
        return ResponseEntity.ok(createdPermission);
    }

    @GetMapping("/permissions/{permissionId}")
    public ResponseEntity<Permission> getPermissionById(@PathVariable Long permissionId) {
        Optional<Permission> permission = rolePermissionService.getPermissionById(permissionId);
        return permission.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/permissions")
    public ResponseEntity<List<Permission>> getAllPermissions() {
        List<Permission> permissions = rolePermissionService.getAllPermissions();
        return ResponseEntity.ok(permissions);
    }

    @PutMapping("/permissions/{permissionId}")
    public ResponseEntity<Permission> updatePermission(@PathVariable Long permissionId, @RequestBody Permission permission) {
        Optional<Permission> updatedPermission = rolePermissionService.updatePermission(permissionId, permission);
        return updatedPermission.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/permissions/{permissionId}")
    public ResponseEntity<Void> deletePermission(@PathVariable Long permissionId) {
        rolePermissionService.deletePermission(permissionId);
        return ResponseEntity.ok().build();
    }
}