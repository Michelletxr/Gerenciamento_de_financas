package com.imd.financas_api.loan.dto;

import com.imd.financas_api.loan.model.Parcel;
import com.imd.financas_api.loan.model.Status;
import com.sun.istack.NotNull;
import lombok.*;
import org.joda.time.DateTimeZone;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ParcelDTO {
    private Status status;
    private UUID id;
    private UUID conta_id;
    private UUID emprestimo_id;
    private String descricao;
    @NotNull
    private Double valor;
    private DateTimeZone data_vencimento;
    private DateTimeZone data_pagamento;

    @Builder
    public ParcelDTO(UUID id, Status status, UUID conta_id, UUID emprestimo_id, String descricao, Double valor,
                     DateTimeZone data_vencimento, DateTimeZone data_pagamento) {
        this.id = id;
        this.status = status;
        this.conta_id = conta_id;
        this.emprestimo_id = emprestimo_id;
        this.descricao = descricao;
        this.valor = valor;
        this.data_vencimento = data_vencimento;
        this.data_pagamento = data_pagamento;
    }

    public record RequestParcel(UUID id, Status status, UUID conta_id, UUID emprestimo_id, String descricao, Double valor,
                                DateTimeZone data_vencimento, DateTimeZone data_pagamento){}

    public record ResponseParcel(UUID id, String descricao, Double valor, Double valor_pagar, String tipo_juros,
                                 Double juros, Date data_inicio, Date data_final, List<Parcel> parcelas){}

    public ParcelDTO buildParcelToResponseLoan(Parcel parcel){
        return new ParcelDTO().builder()
                .id(parcel.getId())
                .status(parcel.getStatus())
                .descricao(parcel.getDescription())
                .valor(parcel.getValue())
                .data_vencimento(parcel.getData_vencimento())
                .data_pagamento(parcel.getData_pagamento())
                .conta_id(parcel.getConta().getId())
                .emprestimo_id(parcel.getEmprestimo().getId())
                .build();

    }
}
