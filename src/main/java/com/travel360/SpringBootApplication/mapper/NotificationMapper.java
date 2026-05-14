package com.travel360.SpringBootApplication.mapper;

import com.travel360.SpringBootApplication.dto.NotificationDTO;
import com.travel360.SpringBootApplication.dto.NotificationResponseDTO;
import com.travel360.SpringBootApplication.entity.Notification;

import java.time.LocalDateTime;

public class NotificationMapper {

    public static Notification toEntity(NotificationDTO dto) {
        return Notification.builder()
                .userId(dto.getUserId())
                .message(dto.getMessage())
                .category(dto.getCategory())
                .status(dto.getStatus())
                .createdDate(LocalDateTime.now())
                .build();
    }

    public static NotificationResponseDTO toResponseDTO(Notification entity) {
        return new NotificationResponseDTO(
                entity.getNotificationId(),
                entity.getUserId(),
                entity.getMessage(),
                entity.getCategory(),
                entity.getStatus(),
                entity.getCreatedDate()
        );
    }
}