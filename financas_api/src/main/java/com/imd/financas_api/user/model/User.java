package com.imd.financas_api.user.model;

import com.imd.financas_api.conta.model.Conta;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Conta> contas;

    @Builder
    public User(UUID id, String name, String login, String password, String email, List<Conta> accountList) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.contas = accountList;
    }
}