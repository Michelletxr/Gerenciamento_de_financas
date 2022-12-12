package com.imd.financas_api.investment.dto;

import com.imd.financas_api.investment.model.Investment;
import com.sun.istack.NotNull;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class InvestmentDTO {
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

    private UUID user;

    @Builder
    public InvestmentDTO(UUID id, Integer id_investment, String corretora, String titulo, String tipo_titulo, Date vencimento_titulo, Date data_investimento, Double investido, String objetivo, UUID user) {
        this.id = id;
        this.id_investment = id_investment;
        this.corretora = corretora;
        this.titulo = titulo;
        this.tipo_titulo = tipo_titulo;
        this.vencimento_titulo = vencimento_titulo;
        this.data_investimento = data_investimento;
        this.investido = investido;
        this.objetivo = objetivo;
        this.user = user;
    }

    public record RequestInvestment(Integer id_investment, String corretora, String titulo, String tipo_titulo, Date vencimento_titulo, Date data_investimento, Double investido, String objetivo,UUID user){}

    public record ResponseInvestment(UUID id, Integer id_investment, String corretora, String titulo, String tipo_titulo, Date vencimento_titulo, Date data_investimento, Double investido, String objetivo){}

    public InvestmentDTO buildInvestmentToResponseInvestment(Investment investment){
        return new InvestmentDTO().builder()
                .id(investment.getId())
                .corretora(investment.getCorretora())
                .titulo(investment.getTitulo())
                .tipo_titulo(investment.getTipo_titulo())
                .vencimento_titulo(investment.getVencimento_titulo())
                .data_investimento(investment.getData_investimento())
                .investido(investment.getInvestido())
                .objetivo(investment.getObjetivo())
                .user(user)
                .build();

    }
}
