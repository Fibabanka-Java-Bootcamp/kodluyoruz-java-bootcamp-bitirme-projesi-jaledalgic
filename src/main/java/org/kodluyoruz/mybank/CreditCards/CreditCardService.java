package org.kodluyoruz.mybank.CreditCards;

import org.kodluyoruz.mybank.Accounts.Account;
import org.kodluyoruz.mybank.Accounts.AccountRepository;
import org.kodluyoruz.mybank.Shopping.Shop;
import org.kodluyoruz.mybank.Shopping.ShopRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.*;

@Component
public class CreditCardService {
    private final CreditCardRepository creditCardRepo;
    private final AccountRepository accountRepo;
    private final ShopRepository shopRepo;

    public CreditCardService(CreditCardRepository creditCardRepo, AccountRepository accountRepo, ShopRepository shopRepo) {
        this.creditCardRepo = creditCardRepo;
        this.accountRepo = accountRepo;
        this.shopRepo = shopRepo;
    }
    public CreditCard create(UUID iban, CreditCard creditCard){
        Account account = accountRepo.findById(iban)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Account not found"+iban));
        if(!creditCardRepo.existsCreditCardByAccount(account)){
            creditCard.setAccount(account);
            creditCard.setCustomer(account.getCustomer());
            return creditCardRepo.save(creditCard);
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"hesap ait kredi kartı bulunmakta.");
        }
    }
    public Map<String, LocalDate> extract(UUID cardNo){
        Map<String,LocalDate> exstre=new HashMap<>();
        List<Shop> shopList=shopRepo.findByToCardNo(cardNo);
        for(int i=0;i<shopList.size();i++) {
            exstre.put(shopList.get(i).getMessage(),shopList.get(i).getShopDate());
        }
        return exstre;
    }
}
