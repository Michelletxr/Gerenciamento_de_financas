package com.imd.financas_api.loan.model;

import com.imd.financas_api.general.Ganho;
import com.imd.financas_api.conta.model.Conta;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name="loan", schema = "public")
public class Loan extends Ganho {
    private Double valor_pagar;
    private String tipo_juros;
    private Double juros;
    private Date data_final;
    @OneToMany(mappedBy = "emprestimo", targetEntity = Parcel.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Parcel> parcelas;

    @Builder
    public Loan(UUID id, String descricao, Double valor, Double valor_pagar, String tipo_juros, Double juros,
                Date data_inicio, Date data_final, List<Parcel> parcels, Conta conta) {
        this.id = id;
        this.description = descricao;
        this.value = valor;
        this.valor_pagar = valor_pagar;
        this.tipo_juros = tipo_juros;
        this.juros = juros;
        this.data_recebimento = data_inicio;
        this.data_final = data_final;
        this.parcelas = parcels;
        this.conta = conta;
    }
}
