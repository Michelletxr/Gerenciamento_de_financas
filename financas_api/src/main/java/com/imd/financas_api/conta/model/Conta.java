package com.imd.financas_api.conta.model;

import com.sun.istack.NotNull;
import lombok.*;
import javax.persistence.*;
import java.util.UUID;


@Data
@NoArgsConstructor
@Entity
@Table(name="conta", schema = "public")
public class Conta {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotNull
    private String name;

    @NotNull
    private Double value;

    @Builder
    public Conta(UUID id, String name, Double value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }
}
