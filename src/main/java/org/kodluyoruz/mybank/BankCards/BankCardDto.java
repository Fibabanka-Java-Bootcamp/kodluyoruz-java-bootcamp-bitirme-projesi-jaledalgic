package org.kodluyoruz.mybank.BankCards;

import lombok.*;
import org.kodluyoruz.mybank.Accounts.Account;
import org.kodluyoruz.mybank.Customers.Customer;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankCardDto {
    private UUID cardNo;
    private int secureNum;
    private LocalDate openingDate;
    private LocalDate lastDate;
    private double money;

}
