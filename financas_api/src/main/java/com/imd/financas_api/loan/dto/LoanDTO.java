package com.imd.financas_api.loan.dto;

import com.imd.financas_api.conta.model.Conta;
import com.imd.financas_api.loan.model.Loan;
import com.sun.istack.NotNull;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class LoanDTO {
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

    @Builder
    public LoanDTO(UUID id, String name, Double valor_pego) {
        this.id = id;
        this.name = name;
        this.valor_pego = valor_pego;
    }

    public record RequestLoan(String name,String login, Double value){}

    public record ResponseLoan(UUID id, String login, Double value){}

    public LoanDTO buildLoanToResponseLoan(Loan loan){
        return new LoanDTO().builder()
                .id(loan.getId())
                .name(loan.getName())
                .valor_pego(loan.getValue())
                .build();

    }
}
