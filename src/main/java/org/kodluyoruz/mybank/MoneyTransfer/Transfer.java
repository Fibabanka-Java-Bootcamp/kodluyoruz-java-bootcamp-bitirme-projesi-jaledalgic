package org.kodluyoruz.mybank.MoneyTransfer;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transfer {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "fromIban")
    private UUID fromIban;

    @Column(name = "toIban")
    private UUID toIban;

    private double amount;
    private String description;

    @Column(name = "transferDate")
    private LocalDate transferDate;

    private String message;

    @Column(name = "transferFee")
    private int transferFee;

    public TransferDto toTransferDto(){
        return TransferDto.builder()
                .fromIban(this.fromIban)
                .toIban(this.toIban)
                .amount(this.amount)
                .description(this.description)
                .build();
    }

}
