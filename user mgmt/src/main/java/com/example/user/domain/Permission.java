package com.example.user.domain;

import javax.persistence.*;

/**
 * Simple JavaBean domain object representing a Permission.
 *
 * @author Sashank RM
 */

@Entity
@Table(name = "permissions")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long permissionId;
    private String permissionName;

    // Getters and setters
    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long id) {
        this.permissionId = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }
}