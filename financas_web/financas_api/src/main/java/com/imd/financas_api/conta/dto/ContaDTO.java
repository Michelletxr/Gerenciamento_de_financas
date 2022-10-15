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
    private Double saldo;

    @Builder
    public ContaDTO(UUID id, String name, Double saldo) {
        this.id = id;
        this.name = name;
        this.saldo = saldo;
    }

    public record RequestUser(String name, Double saldo){}

    public record ResponseUser(UUID id, String login, Double saldo){}

    public ContaDTO buildUserToResponseConta(Conta conta){
        return new ContaDTO().builder()
                .id(conta.getId())
                .name(conta.getName())
                .saldo(conta.getSaldo())
                .build();

    }
}
