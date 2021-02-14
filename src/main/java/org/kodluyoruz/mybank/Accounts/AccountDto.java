package org.kodluyoruz.mybank.Accounts;

import lombok.*;
import org.kodluyoruz.mybank.Customers.Customer;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class AccountDto {
    private LocalDate openingDate;
    private double sumMoney;
    private AccountTypeEnum accountType;
    private CurrencyEnum currency;
    private UUID accountNumber;
    private UUID iban;
    public Account toAccountDto(){
        return Account.builder()
                .iban(this.iban)
                .accountNumber(this.accountNumber)
                .accountType(this.accountType)
                .sumMoney(this.sumMoney)
                .currency(this.currency)
                .openingDate(this.openingDate)
                .build();
    }
}
