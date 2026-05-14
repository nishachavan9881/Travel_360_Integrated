package org.travel360.controller;

import org.travel360.dto.UserRequestDTO;
import org.travel360.dto.UserResponseDTO;
import org.travel360.model.AuditLog;
import org.travel360.service.IamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.access.prepost.PreAuthorize;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/users")
public class AdminUserController {

    private final IamService iamService;

    public AdminUserController(IamService iamService) {
        this.iamService = iamService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public UserResponseDTO createUser(@RequestBody UserRequestDTO dto) {
        return iamService.createUser(dto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return iamService.getAllUsers();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long id) {
        return iamService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public UserResponseDTO updateUser(
            @PathVariable Long id,
            @RequestBody UserRequestDTO dto) {
        return iamService.updateUser(id, dto);
    }

    @PatchMapping("/{id}/deactivate")
    public UserResponseDTO deactivate(@PathVariable Long id) {
        return iamService.deactivateUser(id);
    }

    @PatchMapping("/{id}/activate")
    public UserResponseDTO activate(@PathVariable Long id) {
        return iamService.activateUser(id);
    }
}