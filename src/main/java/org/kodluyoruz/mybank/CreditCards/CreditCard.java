package org.kodluyoruz.mybank.CreditCards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.kodluyoruz.mybank.Accounts.Account;
import org.kodluyoruz.mybank.Customers.Customer;
import org.kodluyoruz.mybank.Shopping.Shop;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "credit_card")
public class CreditCard {
    @Id
    @GeneratedValue
    @Column(name = "cardNo")
    private UUID cardNo;

    private double boundary=0;

    private double debt=0;

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

    @OneToMany(mappedBy = "creditCard")
    private Set<Shop> shops;

    public CreditCardDto toCreditCard(){
        return CreditCardDto.builder()
                .cardNo(this.cardNo)
                .secureNum(this.secureNum)
                .openingDate(this.openingDate)
                .lastDate(this.lastDate)
                .boundary(this.boundary)
                .debt(this.debt)
                .build();

    }

}
