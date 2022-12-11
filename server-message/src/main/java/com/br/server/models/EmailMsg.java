package com.br.server.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "email")
@NoArgsConstructor
public class EmailMsg {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;
    private String username;
    private String emailFrom;
    private String emailTo;
    private String titlet;
    @Column(columnDefinition = "TEXT")
    private String text;
    private LocalDateTime sendDateEmail;
    private StatusEmail statusEmail;

}
