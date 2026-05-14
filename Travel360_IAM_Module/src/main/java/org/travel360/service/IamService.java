package org.travel360.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.travel360.audit.EntityType;
import org.travel360.dto.UserRequestDTO;
import org.travel360.dto.UserResponseDTO;
import org.travel360.mapper.UserMapper;
import org.travel360.model.AuditLog;
import org.travel360.model.User;
import org.travel360.Repository.AuditLogRepository;
import org.travel360.Repository.UserRepository;
import org.springframework.stereotype.Service;
import org.travel360.dto.LoginRequestDTO;
import org.travel360.dto.LoginResponseDTO;
import org.travel360.config.JwtUtil;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.security.crypto.password.PasswordEncoder;


@Service
public class IamService {

    private final UserRepository userRepository;
    private final AuditLogRepository auditLogRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private static final Logger log = LoggerFactory.getLogger(IamService.class);
    public IamService(UserRepository userRepository,
                      AuditLogRepository auditLogRepository,JwtUtil jwtUtil,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.auditLogRepository = auditLogRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder=passwordEncoder;
    }


    public UserResponseDTO createUser(UserRequestDTO dto) {

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());
        user.setPhone(dto.getPhone());
        user.setStatus(dto.getStatus());

        user.setPasswordHash(passwordEncoder.encode(dto.getPassword()));

        User savedUser = userRepository.save(user);

        createAuditLog(savedUser, "USER_CREATED");

        return UserMapper.toResponseDTO(savedUser);
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()//It converts a list of User entities into a list of UserResponseDTO objects.
                .stream()//Convert list into a stream to process elements one by one
                .map(UserMapper::toResponseDTO)
                .collect(Collectors.toList());//Convert stream back into a List
    }

    public Optional<UserResponseDTO> getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserMapper::toResponseDTO);
    }

    public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Optional<User> existingUser = userRepository.findByEmail(dto.getEmail());
        //Email uniqueness is validated before updating user to avoid conflicts
        if (existingUser.isPresent() &&
                !existingUser.get().getUserId().equals(id)) {
            throw new RuntimeException("Email already in use by another user");
        }

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());
        user.setPhone(dto.getPhone());
        user.setStatus(dto.getStatus());

        User updatedUser = userRepository.save(user);
        createAuditLog(updatedUser, "USER_UPDATED");

        return UserMapper.toResponseDTO(updatedUser);
    }

    //Audit log
    public List<AuditLog> getAllAuditLogs() {
        return auditLogRepository.findAll();
    }

    public List<AuditLog> getAuditLogsByUserId(Long userId) {
        return auditLogRepository.findByUserUserId(userId);
    }


    private void createAuditLog(User user, String action) {
        AuditLog log = new AuditLog();
        log.setUser(user);
        log.setAction(action);
        log.setEntityType(EntityType.USER);
        log.setEntityId(user.getUserId());
        log.setTimestamp(LocalDateTime.now());

        auditLogRepository.save(log);
    }

    //  DEACTIVATE USER
    public UserResponseDTO deactivateUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setStatus("INACTIVE");
        User updatedUser = userRepository.save(user);

        createAuditLog(updatedUser, "USER_DEACTIVATED");

        return UserMapper.toResponseDTO(updatedUser);
    }

    //  ACTIVATE USER
    public UserResponseDTO activateUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setStatus("ACTIVE");
        User updatedUser = userRepository.save(user);

        createAuditLog(updatedUser, "USER_ACTIVATED");

        return UserMapper.toResponseDTO(updatedUser);
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        User user = userRepository.findByEmail(loginRequestDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));


        if (!passwordEncoder.matches(
                loginRequestDTO.getPassword(),
                user.getPasswordHash())) {
            throw new RuntimeException("Invalid email or password");
        }


        String token = jwtUtil.generateToken(
                user.getUserId(),
                user.getEmail(),
                user.getRole()
        );

        return new LoginResponseDTO(token);
    }


    public List<AuditLog> getAuditLogsByEntityType(EntityType entityType) {
        return auditLogRepository.findByEntityType(entityType);
    }


}