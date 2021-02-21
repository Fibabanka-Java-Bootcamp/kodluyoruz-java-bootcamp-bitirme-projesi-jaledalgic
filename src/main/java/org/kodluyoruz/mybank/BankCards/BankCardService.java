package org.kodluyoruz.mybank.BankCards;

import org.kodluyoruz.mybank.Accounts.Account;
import org.kodluyoruz.mybank.Accounts.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@Component
public class BankCardService {
    private final BankCardRepository bankCardRepo;
    private final AccountRepository accountRepo;

    public BankCardService(BankCardRepository bankCardRepo, AccountRepository accountRepo) {
        this.bankCardRepo = bankCardRepo;
        this.accountRepo = accountRepo;
    }
    public BankCard create(UUID id, double money)  {
        Account account = accountRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Account not found"+id));
        BankCard bankCard=new BankCard();
        Random random = new Random();
        int x = random.nextInt(900) + 100;
        if(!bankCardRepo.existsBankCardByAccount(account)){
            bankCard.setAccount(account);
            bankCard.setCustomer(account.getCustomer());
            bankCard.setOpeningDate(LocalDate.now());
            bankCard.setLastDate(bankCard.getOpeningDate().plusYears(3));
            bankCard.setSecureNum(x);
            bankCard.setMoney(money);
            account.setSumMoney(bankCard.getMoney());
            return bankCardRepo.save(bankCard);
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "hesaba ait banka kartı bulunmakta.");
        }
    }
}
