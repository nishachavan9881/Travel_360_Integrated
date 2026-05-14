package org.travel360.controller;

import org.travel360.dto.UserRequestDTO;
import org.travel360.dto.UserResponseDTO;

import org.springframework.security.access.prepost.PreAuthorize;

import org.travel360.model.AuditLog;
import org.travel360.service.IamService;
import org.springframework.web.bind.annotation.*;

import org.travel360.audit.EntityType;

import java.util.List;

@RestController
@RequestMapping("/api/iam")
public class Compilance {

    private final IamService iamService;

    public Compilance(IamService iamService) {
        this.iamService = iamService;
    }


    @GetMapping("/compilance/auditlogs/user/{userId}")
    public List<AuditLog> getAuditLogsByUserId(@PathVariable Long userId) {
        return iamService.getAuditLogsByUserId(userId);
    }


    @PreAuthorize("hasAnyRole('ADMIN','COMPLIANCE')")
    @GetMapping("/compilance/auditlogs")
    public List<AuditLog> getAuditLogs(
            @RequestParam(required = false) EntityType entityType) {

        if (entityType != null) {
            return iamService.getAuditLogsByEntityType(entityType);
        }

        return iamService.getAllAuditLogs();
    }


//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping("/users")
//    public List<UserResponseDTO> getAllUsers() {
//        return iamService.getAllUsers();
//    }
//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping("/users/{id}")
//    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
//        Optional<UserResponseDTO> user = iamService.getUserById(id);
//        return user.map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }

//    @PreAuthorize("hasRole('ADMIN')")
//    @PutMapping("/users/{id}")
//    public ResponseEntity<UserResponseDTO> updateUser(
//            @PathVariable Long id,
//            @RequestBody UserRequestDTO userRequestDTO) {
//
//        UserResponseDTO updatedUser = iamService.updateUser(id, userRequestDTO);
//        return ResponseEntity.ok(updatedUser);
//    }
//    @PreAuthorize("hasRole('ADMIN')")
//    @PatchMapping("/users/{id}/deactivate")
//    public ResponseEntity<UserResponseDTO> deactivateUser(@PathVariable Long id) {
//        UserResponseDTO user = iamService.deactivateUser(id);
//        return ResponseEntity.ok(user);
//    }
//    @PreAuthorize("hasRole('ADMIN')")
//    @PatchMapping("/users/{id}/activate")
//    public ResponseEntity<UserResponseDTO> activateUser(@PathVariable Long id) {
//        UserResponseDTO user = iamService.activateUser(id);
//        return ResponseEntity.ok(user);
//    }


//    @GetMapping("/auditlogs")
//    public List<AuditLog> getAllAuditLogs() {
//        return iamService.getAllAuditLogs();
//    }



}
