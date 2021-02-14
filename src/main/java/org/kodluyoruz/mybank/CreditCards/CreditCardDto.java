package org.kodluyoruz.mybank.CreditCards;

import lombok.*;
import org.kodluyoruz.mybank.Accounts.Account;
import org.kodluyoruz.mybank.Customers.Customer;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditCardDto {
    private UUID cardNo;
    private double boundary;
    private double loan;
    public CreditCard toCreditCardDto(){
        return CreditCard.builder()
                .cardNo(this.cardNo)
                .boundary(this.boundary)
                .loan(this.loan)
                .build();
    }
}
