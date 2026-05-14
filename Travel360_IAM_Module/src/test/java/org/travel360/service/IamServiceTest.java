package org.travel360.service;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import org.travel360.service.IamService;
import org.travel360.Repository.UserRepository;
import org.travel360.Repository.AuditLogRepository;
import org.travel360.config.JwtUtil;

import org.travel360.dto.*;
import org.travel360.model.User;

@ExtendWith(MockitoExtension.class)
public class IamServiceTest {


    @Mock
    private UserRepository userRepository;

    @Mock
    private AuditLogRepository auditLogRepository;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private PasswordEncoder passwordEncoder;


    @InjectMocks
    private IamService iamService;

    @Test
    void testCreateUser() {

        UserRequestDTO dto = new UserRequestDTO();
        dto.setName("John");
        dto.setEmail("john@test.com");
        dto.setPassword("123");

        User savedUser = new User();
        savedUser.setUserId(1L);
        savedUser.setName("John");

        Mockito.when(passwordEncoder.encode("123")).thenReturn("hashed");
        Mockito.when(userRepository.save(Mockito.any(User.class)))
                .thenReturn(savedUser);

        UserResponseDTO result = iamService.createUser(dto);

        assertNotNull(result);
        assertEquals("John", result.getName());
    }

    @Test
    void testLogin() {

        LoginRequestDTO dto = new LoginRequestDTO();
        dto.setEmail("admin@test.com");
        dto.setPassword("123");

        User user = new User();
        user.setEmail("admin@test.com");
        user.setPasswordHash("hashed");

        Mockito.when(userRepository.findByEmail("admin@test.com"))
                .thenReturn(Optional.of(user));

        Mockito.when(passwordEncoder.matches("123", "hashed"))
                .thenReturn(true);

        Mockito.when(jwtUtil.generateToken(Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn("token123");

        LoginResponseDTO response = iamService.login(dto);

        assertEquals("token123", response.getToken());
    }
}
