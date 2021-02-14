package org.kodluyoruz.mybank.Shopping;

import lombok.*;
import org.kodluyoruz.mybank.BankCards.BankCard;
import org.kodluyoruz.mybank.CreditCards.CreditCard;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShopDto {
    private UUID toCardNo;
    private double money;
    private String message;
   /* private BankCard bankCard;
     */

    public Shop toShop(){
        return Shop.builder()
                .toCardNo(this.toCardNo)
                .money(this.money)
                //.creditCard(creditCard)
               //.bankCard(this.bankCard)
                 .build();
    }
}
