package com.travel360.notificationservice.controller;

import com.travel360.notificationservice.dto.NotificationDTO;
import com.travel360.notificationservice.dto.NotificationResponseDTO;
import com.travel360.notificationservice.entity.Notification;
import com.travel360.notificationservice.mapper.NotificationMapper;
import com.travel360.notificationservice.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @PostMapping
    public NotificationResponseDTO create(@Valid @RequestBody NotificationDTO dto) {
        Notification notification = NotificationMapper.toEntity(dto);
        Notification saved = service.createNotification(notification);
        return NotificationMapper.toResponseDTO(saved);
    }

    @GetMapping
    public List<NotificationResponseDTO> getAll() {
        List<Notification> notifications = service.getAllNotifications();
        return service.getAllNotifications()
                .stream()
                .map(NotificationMapper::toResponseDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public NotificationResponseDTO getById(@PathVariable Long id) {

        Notification notification = service.getNotificationById(id);

        return NotificationMapper.toResponseDTO(notification);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteNotification(id);
        return "Notification with ID " + id + " deleted successfully.";
    }

    @GetMapping("/user/{userId}")
    public List<Notification> getByUserId(@PathVariable Long userId) {
        return service.getNotificationsByUserId(userId);
    }
}
