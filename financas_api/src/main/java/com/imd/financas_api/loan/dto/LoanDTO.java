package com.imd.financas_api.loan.dto;

import com.imd.financas_api.loan.model.Loan;
import com.sun.istack.NotNull;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class LoanDTO {

    private UUID id;
    @NotNull
    private String descricao;
    private Double valor;
    private Double valor_pagar;

    private String tipo_juros;

    private Double juros;
    private Date data_inicio;

    private Date data_final;

    private List<ParcelDTO> parcelas;

    private UUID conta_id;

    @Builder
    public LoanDTO(UUID id, String descricao, Double valor, Double valor_pagar, List<ParcelDTO> parcelas, String tipo_juros
            , Double juros, Date data_inicio, Date data_final,UUID conta_id) {

        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.valor_pagar = valor_pagar;
        this.parcelas = parcelas;

        this.tipo_juros = tipo_juros;
        this.juros = juros;
        this.data_inicio = data_inicio;
        this.data_final = data_final;
        this.conta_id = conta_id;
    }

    public record RequestLoan(String descricao, Double valor, Double valor_pagar, List<ParcelDTO> parcelas, String tipo_juros, Double juros,
                              Date data_inicio, Date data_final, UUID conta_id){}

    public record ResponseLoan(UUID id, String descricao, Double valor, Double valor_pagar, String tipo_juros, Double juros,
                               Date data_inicio, Date data_final, List<ParcelDTO> parcelas){}

    public LoanDTO buildLoanToResponseLoan(Loan loan){
        return new LoanDTO().builder()
                .id(loan.getId())
                .descricao(loan.getDescription())
                .valor(loan.getValue())
                .valor_pagar(loan.getValor_pagar())
                .tipo_juros(loan.getTipo_juros())
                .juros(loan.getJuros())
                .data_inicio(loan.getData_recebimento())
                .data_final(loan.getData_final())
                //.parcelas(loan.getParcelas())
                .conta_id(loan.getConta().getId())
                .build();

    }
}
