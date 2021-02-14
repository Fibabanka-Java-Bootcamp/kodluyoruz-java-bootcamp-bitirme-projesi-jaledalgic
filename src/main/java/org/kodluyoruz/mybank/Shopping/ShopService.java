package org.kodluyoruz.mybank.Shopping;

import org.kodluyoruz.mybank.Accounts.Account;
import org.kodluyoruz.mybank.Accounts.AccountRepository;
import org.kodluyoruz.mybank.BankCards.BankCard;
import org.kodluyoruz.mybank.BankCards.BankCardRepository;
import org.kodluyoruz.mybank.CreditCards.CreditCard;
import org.kodluyoruz.mybank.CreditCards.CreditCardRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Component
public class ShopService {
    private final ShopRepository shopRepo;
    private final CreditCardRepository creditCardRepo;
    private final BankCardRepository bankCardRepo;
    private final AccountRepository accountRepo;

    public ShopService(ShopRepository shopRepo, CreditCardRepository creditCardRepo, BankCardRepository bankCardRepo, AccountRepository accountRepo) {
        this.shopRepo = shopRepo;
        this.creditCardRepo = creditCardRepo;
        this.bankCardRepo = bankCardRepo;
        this.accountRepo = accountRepo;
    }
    public Shop creditCardShop(Shop shop){
        CreditCard creditCard=creditCardRepo.findByCardNo(shop.getToCardNo())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"bu kart numarasına ait kart bulunamadı."+shop.getToCardNo()));

        double loan=shop.getMoney();
        creditCard.setBoundary(creditCard.getBoundary()-loan);
        creditCard.setLoan(creditCard.getLoan()+loan);
        shop.setCreditCard(creditCard);
        shop.setMessage("kartınızdan " +loan+" TL alışveriş gerçekleştirildi.");
        return shopRepo.save(shop);
    }
    public Shop bankCardShop(Shop shop) {
        BankCard bankCard = bankCardRepo.findByCardNo(shop.getToCardNo())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"bu kart numarasına ait kart bulunamadı."+shop.getToCardNo()));
        double loan = shop.getMoney();
        Account account=bankCard.getAccount();
        if (bankCard.getMoney() >= loan) {
            bankCard.setMoney(bankCard.getMoney() - loan);
            account.setSumMoney(bankCard.getMoney());
            shop.setBankCard(bankCard);
            shop.setShopDate(LocalDate.now());
            shop.setMessage("kartınızdan " + loan + " TL alışveriş gerçekleştirildi.");
            return shopRepo.save(shop);
        }
        else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"bakiye yetersiz");
        }
    }
}
