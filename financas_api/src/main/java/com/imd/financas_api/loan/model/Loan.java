package com.imd.financas_api.loan.model;

import com.sun.istack.NotNull;
import lombok.*;
import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name="emprestimo", schema = "public")
public class Loan {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotNull
    private String name;

    @NotNull
    private Double valor_pego;

    @NotNull
    private Double valor_pago;

    @NotNull
    private Integer parcelas;

    private String tipo_juros;

    @NotNull
    private Double juros;

    @NotNull
    private Double valor_parcela;

    @NotNull
    private Date data_pagamento;

    private String status;

    @NotNull
    @Column(unique = true)
    private Double value;

    @Builder
    public Loan(UUID id, String name, Double valor_pego) {
        this.id = id;
        this.name = name;
        this.valor_pego = valor_pego;
    }
}
