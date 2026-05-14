package com.travel360.SpringBootApplication.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO {

    @NotNull
    private Long userId;

    @NotBlank
    private String message;

    @NotBlank
    private String category;

    @NotBlank
    private String status;
}
