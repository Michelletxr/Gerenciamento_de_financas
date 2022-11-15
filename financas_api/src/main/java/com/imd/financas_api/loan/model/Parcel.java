package com.imd.financas_api.loan.model;

import com.imd.financas_api.abstracts.Gasto;
import com.imd.financas_api.conta.model.Conta;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTimeZone;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name="parcel", schema = "public")
public class Parcel extends Gasto {

    private Status status;
    @ManyToOne(targetEntity = Loan.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "emprestimo_id",  referencedColumnName = "id")
    private Loan emprestimo;

    @Builder
    public Parcel(UUID id, Status status, Loan emprestimo, String description, Double value, Conta conta,
                  DateTimeZone data_vencimento, DateTimeZone data_pagamento) {
        this.status = status;
        this.emprestimo = emprestimo;
    }
}
