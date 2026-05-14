package org.travel360.mapper;

import org.travel360.dto.UserRequestDTO;
import org.travel360.dto.UserResponseDTO;
import org.travel360.model.User;

public class UserMapper {

    private UserMapper() {
        // Prevent instantiation
    }

    //  Convert UserRequestDTO → User Entity
    public static User toEntity(UserRequestDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPasswordHash(dto.getPassword()); // hashing later
        user.setRole(dto.getRole());
        user.setPhone(dto.getPhone());
        user.setStatus(dto.getStatus());
        return user;
    }

    //  Convert User Entity → UserResponseDTO
    public static UserResponseDTO toResponseDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setUserId(user.getUserId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        dto.setPhone(user.getPhone());
        dto.setStatus(user.getStatus());
        return dto;
    }
}