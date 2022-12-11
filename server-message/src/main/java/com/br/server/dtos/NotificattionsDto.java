package com.br.server.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class NotificattionsDto {
    private UUID id;
    private LocalDateTime date;
    @NotBlank
    private String username;
    @NotBlank
    private String title;
    @NotBlank
    private String text;
}
