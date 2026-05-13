package com.travel360.notificationservice.mapper;

import com.travel360.notificationservice.dto.NotificationDTO;
import com.travel360.notificationservice.dto.NotificationResponseDTO;
import com.travel360.notificationservice.entity.Notification;

public class NotificationMapper {
    //DTO -> Entity
    public static Notification toEntity(NotificationDTO dto) {

        Notification notification = new Notification();

        notification.setUserId(dto.getUserId());
        notification.setMessage(dto.getMessage());
        notification.setCategory(dto.getCategory());
        notification.setStatus(dto.getStatus());
        notification.setCreatedDate(java.time.LocalDateTime.now());

        return notification;
    }

    //Entity -> ResponseDTO
    public static NotificationResponseDTO toResponseDTO(Notification notification) {

        NotificationResponseDTO dto = new NotificationResponseDTO();

        dto.setNotificationId(notification.getNotificationId());
        dto.setUserId(notification.getUserId());
        dto.setMessage(notification.getMessage());
        dto.setCategory(notification.getCategory());
        dto.setStatus(notification.getStatus());
        dto.setCreatedDate(notification.getCreatedDate());

        return dto;
    }
}
