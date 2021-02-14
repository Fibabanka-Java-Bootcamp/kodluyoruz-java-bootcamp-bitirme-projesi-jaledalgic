package org.kodluyoruz.mybank.Accounts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.kodluyoruz.mybank.BankCards.BankCard;
import org.kodluyoruz.mybank.Customers.Customer;
import org.kodluyoruz.mybank.CreditCards.CreditCard;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Locale;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@Entity
public class Account {
    @Id
    @GeneratedValue
    @Column(name = "iban")
    private UUID iban;

    @Column(name = "accountNumber")
    private UUID accountNumber;

    @Column(name = "openingDate")
    private LocalDate openingDate;

    @Column(name = "sumMoney")
    private double sumMoney=0;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private AccountTypeEnum accountType;

    @Enumerated(EnumType.STRING)
    private CurrencyEnum currency;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "costumer_id")
    private Customer customer;

    @OneToOne(mappedBy = "account",cascade = CascadeType.REMOVE)
    private CreditCard creditCard;

    @OneToOne(mappedBy = "account",cascade = CascadeType.REMOVE)
    private BankCard bankCard;


    public Account() {

    }
    public AccountDto toAccount(){
        return AccountDto.builder()
                .iban(this.iban)
                .accountNumber(accountNumber)
                .accountType(this.accountType)
                .sumMoney(this.sumMoney)
                .currency(this.currency)
                .openingDate(this.openingDate)
                .build();
    }

}
