package com.imd.financas_api.abstracts;

import com.imd.financas_api.conta.model.Conta;
import com.sun.istack.NotNull;
import org.joda.time.DateTimeZone;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.UUID;

public abstract class Ganho {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotNull
    private String descricao;
    @NotNull
    private Double valor;
    @OneToMany
    private Conta conta;
    private DateTimeZone data_entrada;
}