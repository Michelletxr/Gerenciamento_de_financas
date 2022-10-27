package com.imd.financas_api.investment.model;

import com.sun.istack.NotNull;
import lombok.*;
import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name="investimento", schema = "public")
public class Investment {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private Integer id_investment;
    @NotNull
    private String corretora;

    private String titulo;

    private String tipo_titulo;

    private Date vencimento_titulo;

    private Date data_investimento;

    private Double investido;

    private String objetivo;


    @Builder
    public Investment(UUID id, Integer id_investment, String corretora, String titulo, String tipo_titulo, Date vencimento_titulo, Date data_investimento, Double investido, String objetivo) {
        this.id = id;
        this.id_investment = id_investment;
        this.corretora = corretora;
        this.titulo = titulo;
        this.tipo_titulo = tipo_titulo;
        this.vencimento_titulo = vencimento_titulo;
        this.data_investimento = data_investimento;
        this.investido = investido;
        this.objetivo = objetivo;
    }
}
