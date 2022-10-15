package com.imd.financas_api.spending.model;

import com.imd.financas_api.conta.model.Conta;
import com.imd.financas_api.user.model.User;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTimeZone;

import javax.persistence.*;
import java.util.UUID;


@Data
@NoArgsConstructor
@Entity
@Table(name="spending", schema = "public")
public class Spending {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotNull
    private String name;

    @NotNull
    private Double value;
    @OneToMany
    @NotNull
    private User user;

    @OneToMany
    private Conta account;
    private DateTimeZone date_expiration;
    private boolean paid;


    @Builder
    public Spending(UUID id, String name, Double value, User user, Conta account, DateTimeZone date_expiration , boolean paid) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.user = user;
        this.account = account;
        this.date_expiration = date_expiration;
        this.paid = paid;
    }
}
