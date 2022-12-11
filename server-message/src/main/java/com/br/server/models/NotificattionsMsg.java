package com.br.server.models;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "notificattions")
@NoArgsConstructor
public class NotificattionsMsg {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private LocalDateTime date;
    private String username;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String text;
}
