package org.kodluyoruz.mybank.BankCards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.kodluyoruz.mybank.Accounts.Account;
import org.kodluyoruz.mybank.Customers.Customer;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class BankCard {

    @Id
    @GeneratedValue
    @Column(name = "cardNo")
    private UUID cardNo;

    private double money=0;

    @Column(name = "secureNum")
    private int secureNum;

    private LocalDate openingDate;

    private LocalDate lastDate;

    @OneToOne
    @JoinColumn(name = "account_iban", referencedColumnName = "iban")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "costumer_id")
    private Customer customer;

    public BankCardDto toBankCard() {
        return BankCardDto.builder()
                .cardNo(this.cardNo)
                .secureNum(this.secureNum)
                .openingDate(this.openingDate)
                .lastDate(this.lastDate)
                .money(this.money)
                .build();
    }

}
