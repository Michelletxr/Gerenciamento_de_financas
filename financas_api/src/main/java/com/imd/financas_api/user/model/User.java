package com.imd.financas_api.user.model;

import com.sun.istack.NotNull;
import lombok.*;
import javax.persistence.*;
import java.util.UUID;


@Data
@NoArgsConstructor
@ToString(exclude = "password")
@Entity
@Table(name="user", schema = "public")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotNull
    private String name;

    @NotNull
    @Column(unique = true)
    private String login;

    @NotNull
    private String password;
    private String email;

    @Builder
    public User(UUID id, String name, String login, String password, String email) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
    }
}
