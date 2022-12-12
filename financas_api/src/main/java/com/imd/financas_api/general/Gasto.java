package com.imd.financas_api.general;

import com.imd.financas_api.conta.model.Conta;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTimeZone;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@MappedSuperclass
public abstract class Gasto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String description;
    @NotNull
    private Double value;

    @ManyToOne
    @JoinColumn(name = "conta_id",  referencedColumnName = "id")
    private Conta conta;
    private DateTimeZone data_vencimento;
    private DateTimeZone data_pagamento;
}
