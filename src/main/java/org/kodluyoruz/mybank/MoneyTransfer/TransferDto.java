package org.kodluyoruz.mybank.MoneyTransfer;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferDto {
    private UUID fromIban;
    private UUID toIban;
    private double amount;
    private String description;

    public Transfer toTransfer(){
        return Transfer.builder()
                .fromIban(this.fromIban)
                .toIban(this.toIban)
                .amount(this.amount)
                .description(this.description) .build();
    }
}
