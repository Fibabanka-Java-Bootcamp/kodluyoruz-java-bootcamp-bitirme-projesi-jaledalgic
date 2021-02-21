package org.kodluyoruz.mybank.CreditCards;

import org.kodluyoruz.mybank.Accounts.Account;
import org.kodluyoruz.mybank.Accounts.AccountRepository;
import org.kodluyoruz.mybank.Debt.Debt;
import org.kodluyoruz.mybank.Debt.DebtRepository;
import org.kodluyoruz.mybank.Shopping.Shop;
import org.kodluyoruz.mybank.Shopping.ShopRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.*;

@Component
public class CreditCardService {
    private final CreditCardRepository creditCardRepo;
    private final AccountRepository accountRepo;
    private final ShopRepository shopRepo;
    private final DebtRepository debtRepo;

    public CreditCardService(CreditCardRepository creditCardRepo, AccountRepository accountRepo, ShopRepository shopRepo, DebtRepository debtRepo) {
        this.creditCardRepo = creditCardRepo;
        this.accountRepo = accountRepo;
        this.shopRepo = shopRepo;
        this.debtRepo = debtRepo;
    }

    public CreditCardDto create(UUID iban, double boundary) {
        Account account = accountRepo.findById(iban)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found" + iban));
        Random random = new Random();
        int x = random.nextInt(900) + 100;
        CreditCard creditCard=new CreditCard();
        if (!creditCardRepo.existsCreditCardByAccount(account)) {
            creditCard.setAccount(account);
            creditCard.setCustomer(account.getCustomer());
            creditCard.setSecureNum(x);
            creditCard.setOpeningDate(LocalDate.now());
            creditCard.setLastDate(creditCard.getOpeningDate().plusYears(3));
            creditCard.setDebt(0);
            creditCard.setBoundary(boundary);
            return creditCardRepo.save(creditCard).toCreditCard();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "hesap ait kredi kartı bulunmakta.");
        }
    }

    public Map<String, LocalDate> extract(UUID cardNo) {
        Map<String, LocalDate> exstre = new HashMap<>();
        List<Shop> shopList = shopRepo.findByToCardNo(cardNo);
        if(shopList.size()!=0) {
            for (int i = 0; i < shopList.size(); i++) {
                exstre.put(shopList.get(i).getMessage(), shopList.get(i).getShopDate());
            }
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"herhangi bir alışveriş yok");
        }
        return exstre;
    }


}
