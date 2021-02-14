package org.kodluyoruz.mybank.BankCards;

import lombok.*;
import org.kodluyoruz.mybank.Accounts.Account;
import org.kodluyoruz.mybank.Customers.Customer;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankCardDto {
    private double money;
    private UUID cardNo;
    public BankCard toBankCardDto() {
        return BankCard.builder()
                .cardNo(this.cardNo)
                .money(this.money)
                .build();
    }
}
