package com.travel360.SpringBootApplication.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationResponseDTO {

    private Long notificationId;
    private Long userId;
    private String message;
    private String category;
    private String status;
    private LocalDateTime createdDate;
}