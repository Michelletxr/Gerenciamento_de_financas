package com.imd.financas_api.loan.dto;

import com.imd.financas_api.loan.model.Loan;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class LoanDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    public LoanDTO(UUID id, String descricao, Double valor, Double valor_pagar, Integer parcelas, String tipo_juros, Double juros, Double valor_parcela, Date data_inicio, Date data_final, Integer[] parcela) {
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

    public record RequestLoan(String descricao, Double valor, Double valor_pagar, Integer parcelas, String tipo_juros, Double juros, Double valor_parcela, Date data_inicio, Date data_final, Integer[] parcela){}

    public record ResponseLoan(UUID id, String descricao, Double valor, Double valor_pagar, Integer parcelas, String tipo_juros, Double juros, Double valor_parcela, Date data_inicio, Date data_final, Integer[] parcela){}

    public LoanDTO buildLoanToResponseLoan(Loan loan){
        return new LoanDTO().builder()
                .id(loan.getId())
                .descricao(loan.getDescricao())
                .valor(loan.getValor())
                .valor_pagar(loan.getValor_pagar())
                .parcelas(loan.getParcelas())
                .tipo_juros(loan.getTipo_juros())
                .juros(loan.getJuros())
                .valor_parcela(loan.getValor_parcela())
                .data_inicio(loan.getData_inicio())
                .data_final(loan.getData_final())
                .parcela(loan.getParcela())
                .build();

    }
}
