package com.imd.financas_api.conta.dto;

import com.imd.financas_api.conta.model.Conta;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ContaDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotNull
    private String name;
    @NotNull
    private Double value;

    private UUID user_id;

    @Builder
    public ContaDTO(UUID id, String name, Double value, UUID user_id) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.user_id = user_id;
    }

    public record RequestConta(String name, Double value,String user_id){}

    public record ResponseConta(UUID id, String login, Double value){}

    public ContaDTO buildContaToResponseConta(Conta conta){
        return new ContaDTO().builder()
                .id(conta.getId())
                .name(conta.getName())
                .value(conta.getValue())
                .user_id(conta.getId())
                .build();

    }
}
