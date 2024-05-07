package com.example.role.repository;

import com.example.role.domain.Permission;
import com.example.role.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
}