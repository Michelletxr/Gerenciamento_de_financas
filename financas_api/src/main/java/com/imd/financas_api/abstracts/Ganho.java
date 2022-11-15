package com.imd.financas_api.abstracts;

import com.imd.financas_api.conta.model.Conta;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@MappedSuperclass
public abstract class Ganho {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected UUID id;
    protected String description;
    @NotNull
    protected Double value;
    @ManyToOne
    @JoinColumn(name = "conta_id",  referencedColumnName = "id")
    protected Conta conta;
    protected Date data_recebimento;
}