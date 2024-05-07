package com.example.attendance.service;

import com.example.attendance.domain.Attendance;
import com.example.attendance.repository.AttendanceRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class UserRoleService {

    @Autowired
    private RestTemplate restTemplate;

    public Set<String> getPermissionsForUser(Long userId) {
        // Fetch the user from the User Management Service
        String url = "http://localhost:8090/users/" + userId;
        User user = restTemplate.getForObject(url, User.class);

        // Extract permissions from the user's roles
        Set<String> permissions = new HashSet<>();
        if (user != null) {
            permissions = user.getRoles().stream()
                    .flatMap(role -> role.getPermissions().stream())
                    .map(Permission::getPermissionName)
                    .collect(Collectors.toSet());
        }

        return permissions;
    }
}
@Setter
@Getter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
class User {
    private Long userId;
    private String username;
    private String password;
    private Set<Role> roles;

}

@Setter
@Getter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
class Role {
    private Long roleId;
    private String name;
    private Set<Permission> permissions;
}
@Setter
@Getter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
class Permission {
    private Long permissionId;
    private String permissionName;
}

