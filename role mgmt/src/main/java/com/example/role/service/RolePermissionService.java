package com.example.role.service;

import com.example.role.domain.Permission;
import com.example.role.domain.Role;
import com.example.role.repository.PermissionRepository;
import com.example.role.repository.RoleRepository;
import com.example.role.web.dto.requestDto.PermissionRequestDTO;
import com.example.role.web.dto.requestDto.RoleRequestDTO;
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
    public Role createRole(RoleRequestDTO roleRequestDTO) {
        Role role = mapRequestDTOToRole(roleRequestDTO);
        return roleRepository.save(role);
    }

    @Transactional
    public Optional<Role> updateRole(Long roleId, RoleRequestDTO newRoleRequestDTO) {
        return roleRepository.findById(roleId).map(role -> {
            role.setName(newRoleRequestDTO.getName());
            Set<Permission> permissions = new HashSet<>(permissionRepository.findAllById(
                    newRoleRequestDTO.getPermissionIds().stream().map(Permission::getPermissionId).collect(Collectors.toList())
            ));
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

    public Role deleteRole(Long roleId) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new IllegalArgumentException("Role not found"));
        roleRepository.deleteById(roleId);
        return role;
    }

    @Transactional
    public Permission createPermission(PermissionRequestDTO permissionRequestDTO) {
        Permission permission = mapRequestDTOToPermission(permissionRequestDTO);
        return permissionRepository.save(permission);
    }

    public Optional<Permission> getPermissionById(Long permissionId) {
        return permissionRepository.findById(permissionId);
    }

    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    @Transactional
    public Optional<Permission> updatePermission(Long permissionId, PermissionRequestDTO permissionRequestDTO) {
        return permissionRepository.findById(permissionId).map(permission -> {
            permission.setPermissionName(permissionRequestDTO.getPermissionName());
            return permissionRepository.save(permission);
        });
    }

    @Transactional
    public Permission deletePermission(Long permissionId) {
        Permission permission = permissionRepository.findById(permissionId).orElseThrow(() -> new IllegalArgumentException("Permission not found"));
        permissionRepository.deleteById(permissionId);
        return permission;
    }

    // Helper methods to map DTOs to entities
    private Role mapRequestDTOToRole(RoleRequestDTO roleRequestDTO) {
        Role role = new Role();
        role.setName(roleRequestDTO.getName());
        role.setPermissions(new HashSet<>(permissionRepository.findAllById(
                roleRequestDTO.getPermissionIds().stream().map(Permission::getPermissionId).collect(Collectors.toList()))));
        return role;
    }

    private Permission mapRequestDTOToPermission(PermissionRequestDTO permissionRequestDTO) {
        Permission permission = new Permission();
        permission.setPermissionName(permissionRequestDTO.getPermissionName());
        return permission;
    }
}
