package com.example.role.web.controller;

import com.example.role.domain.Permission;
import com.example.role.domain.Role;
import com.example.role.service.RolePermissionService;
import com.example.role.web.dto.requestDto.PermissionRequestDTO;
import com.example.role.web.dto.requestDto.RoleRequestDTO;
import com.example.role.web.dto.responseDto.ResponseDto;
import com.example.role.web.exception.ExceptionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/roles-permissions")
public class RolePermissionController {

    @Autowired
    private RolePermissionService rolePermissionService;

    @PostMapping("/roles")
    public ResponseEntity<ResponseDto> createRole(@RequestBody RoleRequestDTO roleRequestDTO) {
        ResponseDto response = new ResponseDto();
        try {
            Role createdRole = rolePermissionService.createRole(roleRequestDTO);
            response.setSuccess(createdRole);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setError(new ExceptionResponse(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error, try again", e.getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/roles/{roleId}")
    public ResponseEntity<ResponseDto> getRoleById(@PathVariable Long roleId) {
        ResponseDto response = new ResponseDto();
        Optional<Role> role = rolePermissionService.getRoleById(roleId);
        if (role.isPresent()) {
            response.setSuccess(role.get());
            return ResponseEntity.ok(response);
        } else {
            response.setError(new ExceptionResponse(new Date(), HttpStatus.NOT_FOUND.value(), "Role not found", "Role not found"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/roles")
    public ResponseEntity<ResponseDto> getAllRoles() {
        ResponseDto response = new ResponseDto();
        List<Role> roles = rolePermissionService.getAllRoles();
        response.setSuccess(roles);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/roles/{roleId}")
    public ResponseEntity<ResponseDto> updateRole(@PathVariable Long roleId, @RequestBody RoleRequestDTO roleRequestDTO) {
        ResponseDto response = new ResponseDto();
        try {
            Optional<Role> updatedRole = rolePermissionService.updateRole(roleId, roleRequestDTO);
            if (updatedRole.isPresent()) {
                response.setSuccess(updatedRole.get());
                return ResponseEntity.ok(response);
            } else {
                response.setError(new ExceptionResponse(new Date(), HttpStatus.NOT_FOUND.value(), "Role not found", "Role not found"));
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            response.setError(new ExceptionResponse(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error, try again", e.getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/roles/{roleId}")
    public ResponseEntity<ResponseDto> deleteRole(@PathVariable Long roleId) {
        ResponseDto response = new ResponseDto();
        try{
            response.setSuccess(rolePermissionService.deleteRole(roleId));
            return ResponseEntity.ok(response);
        }
        catch (Exception e){
            response.setError(new ExceptionResponse(new Date(), HttpStatus.NOT_FOUND.value(), "Role not found", e.getMessage()));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping("/permissions")
    public ResponseEntity<ResponseDto> createPermission(@RequestBody PermissionRequestDTO permissionRequestDTO) {
        ResponseDto response = new ResponseDto();
        try {
            Permission createdPermission = rolePermissionService.createPermission(permissionRequestDTO);
            response.setSuccess(createdPermission);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setError(new ExceptionResponse(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error, try again", e.getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/permissions/{permissionId}")
    public ResponseEntity<ResponseDto> getPermissionById(@PathVariable Long permissionId) {
        ResponseDto response = new ResponseDto();
        Optional<Permission> permission = rolePermissionService.getPermissionById(permissionId);
        if (permission.isPresent()) {
            response.setSuccess(permission.get());
            return ResponseEntity.ok(response);
        } else {
            response.setError(new ExceptionResponse(new Date(), HttpStatus.NOT_FOUND.value(), "Permission not found", "Permission not found"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/permissions")
    public ResponseEntity<ResponseDto> getAllPermissions() {
        ResponseDto response = new ResponseDto();
        List<Permission> permissions = rolePermissionService.getAllPermissions();
        response.setSuccess(permissions);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/permissions/{permissionId}")
    public ResponseEntity<ResponseDto> updatePermission(@PathVariable Long permissionId, @RequestBody PermissionRequestDTO permissionRequestDTO) {
        ResponseDto response = new ResponseDto();
        try {
            Optional<Permission> updatedPermission = rolePermissionService.updatePermission(permissionId, permissionRequestDTO);
            if (updatedPermission.isPresent()) {
                response.setSuccess(updatedPermission.get());
                return ResponseEntity.ok(response);
            } else {
                response.setError(new ExceptionResponse(new Date(), HttpStatus.NOT_FOUND.value(), "Permission not found", "Permission not found"));
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            response.setError(new ExceptionResponse(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error, try again", e.getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/permissions/{permissionId}")
    public ResponseEntity<ResponseDto> deletePermission(@PathVariable Long permissionId) {

        ResponseDto response = new ResponseDto();
        try{
            response.setSuccess(rolePermissionService.deletePermission(permissionId));
            return ResponseEntity.ok(response);
        }
        catch (Exception e){
            response.setError(new ExceptionResponse(new Date(), HttpStatus.NOT_FOUND.value(), "Permission not found", e.getMessage()));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

}
