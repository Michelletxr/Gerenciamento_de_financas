package com.imd.financas_api.spending.dto;

import com.imd.financas_api.spending.model.Spending;
import com.sun.istack.NotNull;
import lombok.*;
import org.joda.time.DateTimeZone;

import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class SpendingDto {
    private UUID id;
    @NotNull
    private String name;
    @NotNull
    private Double value;
    private UUID user_id;
    private UUID account_id;
    private DateTimeZone date_expiration;
    private boolean paid;

    @Builder
    public SpendingDto(UUID id, String name, Double value, UUID user_id, UUID account_id ,
                       DateTimeZone date_expiration, boolean paid) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.user_id = user_id;
        this.account_id = account_id;
        this.date_expiration = date_expiration;
        this.paid = paid;
    }

    public record RequestSpending(String name, Double value, UUID user_id, UUID account_id, DateTimeZone date_expiration, boolean paid ){}

    public record ResponseSpending(UUID id, String name, Double value, DateTimeZone date_expiration, boolean paid){}

    public SpendingDto buildSpendingToResponseSpending(Spending spending){
        return new SpendingDto().builder()
                .id(spending.getId())
                .name(spending.getName())
                .value(spending.getValue())
                .user_id(spending.getUser().getId())
                .account_id(spending.getAccount().getId())
                .date_expiration(spending.getDate_expiration())
                .paid(spending.isPaid())
                .build();

    }
}
