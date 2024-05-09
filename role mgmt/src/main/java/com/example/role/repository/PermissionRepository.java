package com.example.role.repository;

import com.example.role.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    List<Permission> findByPermissionNameIn(List<String> permissionName);
    Permission findByPermissionName(String permissionName);
}