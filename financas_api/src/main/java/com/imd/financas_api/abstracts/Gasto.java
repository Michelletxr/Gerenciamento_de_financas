package com.imd.financas_api.abstracts;

import com.imd.financas_api.conta.model.Conta;
import com.sun.istack.NotNull;
import org.joda.time.DateTimeZone;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.UUID;

public abstract class Gasto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotNull
    private String description;
    @NotNull
    private Double value;
    @OneToMany
    private Conta account;
    private DateTimeZone data_vencimento;
    private DateTimeZone data_pagamento;
    private boolean pago;
}
