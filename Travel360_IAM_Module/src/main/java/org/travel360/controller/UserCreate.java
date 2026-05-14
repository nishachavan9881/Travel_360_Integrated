package org.travel360.controller;

import org.travel360.dto.UserRequestDTO;
import org.travel360.dto.UserResponseDTO;
import org.travel360.model.AuditLog;
import org.travel360.service.IamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import org.travel360.audit.EntityType;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/iam")
public class UserCreate {
    private final IamService iamService;

    public UserCreate(IamService iamService) {
        this.iamService = iamService;
    }

    //    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/users")
    public UserResponseDTO createUser(@RequestBody UserRequestDTO userRequestDTO) {
        return iamService.createUser(userRequestDTO);
    }

    @GetMapping("/users")
    public List<UserResponseDTO> getAllUsers() {
        return iamService.getAllUsers();
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable Long id,
            @RequestBody UserRequestDTO userRequestDTO) {

        UserResponseDTO updatedUser = iamService.updateUser(id, userRequestDTO);
        return ResponseEntity.ok(updatedUser);
    }
}
