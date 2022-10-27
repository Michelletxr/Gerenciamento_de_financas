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
    private String descricao;

    private Double valor;

    private Double valor_pagar;

    private Integer parcelas;

    private String tipo_juros;

    private Double juros;

    private Double valor_parcela;

    private Date data_inicio;

    private Date data_final;

    private Integer[] parcela;

    @Builder
    public Loan(UUID id, String descricao, Double valor, Double valor_pagar, Integer parcelas, String tipo_juros, Double juros, Double valor_parcela, Date data_inicio, Date data_final, Integer[] parcela) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.valor_pagar = valor_pagar;
        this.parcelas = parcelas;
        this.tipo_juros = tipo_juros;
        this.juros = juros;
        this.valor_parcela = valor_parcela;
        this.data_inicio = data_inicio;
        this.data_final = data_final;
        this.parcela = parcela;
    }
}
