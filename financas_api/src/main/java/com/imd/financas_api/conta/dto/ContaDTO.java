package com.imd.financas_api.conta.dto;

import com.imd.financas_api.conta.model.Conta;
import com.sun.istack.NotNull;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ContaDTO {
    private UUID id;
    @NotNull
    private String name;
    @NotNull
    private Double value;

    @Builder
    public ContaDTO(UUID id, String name, Double value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public record RequestConta(String name,String login, Double value){}

    public record ResponseConta(UUID id, String login, Double value){}

    public ContaDTO buildContaToResponseConta(Conta conta){
        return new ContaDTO().builder()
                .id(conta.getId())
                .name(conta.getName())
                .value(conta.getValue())
                .build();

    }
}