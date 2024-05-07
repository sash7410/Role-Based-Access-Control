package com.example.role.service;

import com.example.role.domain.Permission;
import com.example.role.domain.Role;
import com.example.role.repository.PermissionRepository;
import com.example.role.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RolePermissionService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Transactional
    public Role createRole(Role role) {
        Set<Permission> permissions = new HashSet<>(permissionRepository.findAllById(
                role.getPermissions().stream().map(Permission::getPermissionId).collect(Collectors.toList())
        ));
        role.setPermissions(permissions);
        return roleRepository.save(role);
    }

    @Transactional
    public Optional<Role> updateRole(Long roleId, Role newRoleData) {
        Set<Permission> permissions = new HashSet<>(permissionRepository.findAllById(
                newRoleData.getPermissions().stream().map(Permission::getPermissionId).collect(Collectors.toList())
        ));

        return roleRepository.findById(roleId).map(role -> {
            role.setName(newRoleData.getName());
            role.setPermissions(permissions);
            return roleRepository.save(role);
        });
    }

    public Optional<Role> getRoleById(Long roleId) {
        return roleRepository.findById(roleId);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public void deleteRole(Long roleId) {
        roleRepository.deleteById(roleId);
    }

    @Transactional
    public Permission createPermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    public Optional<Permission> getPermissionById(Long permissionId) {
        return permissionRepository.findById(permissionId);
    }

    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    @Transactional
    public Optional<Permission> updatePermission(Long permissionId, Permission newPermission) {
        return permissionRepository.findById(permissionId).map(permission -> {
            permission.setPermissionName(newPermission.getPermissionName());
            return permissionRepository.save(permission);
        });
    }

    @Transactional
    public void deletePermission(Long permissionId) {
        permissionRepository.deleteById(permissionId);
    }
}