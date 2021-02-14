package org.kodluyoruz.mybank.BankCards;

import org.kodluyoruz.mybank.Accounts.AccountDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/bankcard")
public class BankCardController {
    private final BankCardService bankCardService;

    public BankCardController(BankCardService bankCardService) {
        this.bankCardService = bankCardService;
    }

    @PostMapping("/{iban}")
    @ResponseStatus(HttpStatus.CREATED)
    public BankCardDto create(@PathVariable UUID iban, @RequestBody BankCardDto bankCardDto){
        return bankCardService.create(iban, bankCardDto.toBankCardDto()).toBankCard();
    }
}
