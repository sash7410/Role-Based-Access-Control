package com.example.attendance.service;

import com.example.attendance.web.dto.responseDto.ResponseDto;
import com.example.attendance.web.exception.ExceptionResponse;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@Service
public class UserRoleService {

    @Autowired
    private RestTemplate restTemplate;

    public Set<String> getPermissionsForUser(Long userId) {
        return getPermissions(getUser(userId));
    }
    public String getUserName(Long userId) {
        return getUser(userId).getUsername();
    }
    public User getUser(Long userId) {
        // Fetch the user from the User Management Service
        String url = "http://localhost:8090/users/" + userId;
        ResponseEntity<ResponseDto> responseEntity = restTemplate.getForEntity(url, ResponseDto.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            ResponseDto responseDto = responseEntity.getBody();
            if (responseDto != null && responseDto.getSuccess() != null) {
                Object successObject = responseDto.getSuccess();
                if (successObject instanceof Map) {
                    return getUser(successObject);
                } else {
                    throw new IllegalStateException("Success field is not of type Map.");
                }
            } else {
                throw new IllegalStateException("Success field is empty in the response.");
            }
        } else {
            //Fix this to throw same exception
            ExceptionResponse error = Objects.requireNonNull(responseEntity.getBody()).getError();
            throw new IllegalStateException("Error occurred: " + error.getMessage());
        }
    }

    public User getUser(Object successObject) {
        Map<String, Object> successMap = (Map<String, Object>) successObject;
        User user = new User();
        user.setUserId(((Number) successMap.get("userId")).longValue());
        user.setUsername((String) successMap.get("username"));
        List<Map<String, Object>> rolesMaps = (List<Map<String, Object>>) successMap.get("roles");
        Set<Role> roles = new HashSet<>();
        for (Map<String, Object> roleMap : rolesMaps) {
            Role role = new Role();
            role.setRoleId(((Number) roleMap.get("roleId")).longValue());
            role.setName((String) roleMap.get("name"));
            List<Map<String, Object>> permissionsMaps = (List<Map<String, Object>>) roleMap.get("permissions");
            Set<Permission> permissions = new HashSet<>();
            for (Map<String, Object> permissionMap : permissionsMaps) {
                Permission permission = new Permission();
                permission.setPermissionId(((Number) permissionMap.get("permissionId")).longValue());
                permission.setPermissionName((String) permissionMap.get("permissionName"));
                permissions.add(permission);
            }
            role.setPermissions(permissions);
            roles.add(role);
        }
        user.setRoles(roles);
        return user;
    }
    public Set<String> getPermissions(User user) {
        Set<String> permissions = new HashSet<>();
        for (Role role : user.getRoles()) {
            for (Permission permission : role.getPermissions()) {
                permissions.add(permission.getPermissionName());
            }
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

