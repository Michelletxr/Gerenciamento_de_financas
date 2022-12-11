package com.br.server.dtos;

import com.br.server.models.StatusEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class EmailDto {
    private UUID id;
    @NotBlank
    private String username;
    @NotBlank
    @Email
    private String emailFrom;
    @NotBlank
    @Email
    private String emailTo;
    @NotBlank
    private String title;
    @NotBlank
    private String text;
    private LocalDateTime sendDateEmail;
    private StatusEmail statusEmail;

}
