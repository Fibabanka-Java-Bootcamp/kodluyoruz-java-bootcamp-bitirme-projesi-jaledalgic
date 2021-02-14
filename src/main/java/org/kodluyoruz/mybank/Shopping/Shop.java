package org.kodluyoruz.mybank.Shopping;

import lombok.*;
import org.kodluyoruz.mybank.BankCards.BankCard;
import org.kodluyoruz.mybank.CreditCards.CreditCard;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;
import java.util.spi.LocaleServiceProvider;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Shop {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "toCardNo")
    private UUID toCardNo;

    private double money;
    private String message;

    @Column(name = "shopDate")
    private LocalDate shopDate;

    @ManyToOne
    @JoinColumn(name = "creditcard_id")
    private CreditCard creditCard;

    @ManyToOne
    @JoinColumn(name = "bankcardNo")
    private BankCard bankCard;


    public ShopDto toShopDto(){
        return ShopDto.builder()
                .toCardNo(this.toCardNo)
                .money(this.money)
                .message(this.message)
                .build();
    }

}
