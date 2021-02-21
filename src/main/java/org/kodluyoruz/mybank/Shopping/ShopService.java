package org.kodluyoruz.mybank.Shopping;

import org.kodluyoruz.mybank.Accounts.Account;
import org.kodluyoruz.mybank.BankCards.BankCard;
import org.kodluyoruz.mybank.BankCards.BankCardRepository;
import org.kodluyoruz.mybank.CreditCards.CreditCard;
import org.kodluyoruz.mybank.CreditCards.CreditCardRepository;
import org.kodluyoruz.mybank.Debt.DebtRepository;
import org.kodluyoruz.mybank.Debt.DebtService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Component
public class ShopService {
    private final ShopRepository shopRepo;
    private final CreditCardRepository creditCardRepo;
    private final BankCardRepository bankCardRepo;
    private final DebtService debtService;

    public ShopService(ShopRepository shopRepo, CreditCardRepository creditCardRepo,
                       BankCardRepository bankCardRepo, DebtService debtService) {
        this.shopRepo = shopRepo;
        this.creditCardRepo = creditCardRepo;
        this.bankCardRepo = bankCardRepo;
        this.debtService = debtService;
    }
    public Shop creditCardShop(Shop shop){
        CreditCard creditCard=creditCardRepo.findByCardNo(shop.getToCardNo())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"bu kart numarasına ait kart bulunamadı."+shop.getToCardNo()));

        double debt=shop.getMoney();
        creditCard.setBoundary(creditCard.getBoundary()-debt);
        creditCard.setDebt(creditCard.getDebt()+debt);
        shop.setCreditCard(creditCard);
        shop.setMessage("kartınızdan " +debt+" TL alışveriş gerçekleştirildi.");
        debtService.debtAdd(shop);
        return shopRepo.save(shop);
    }
    public Shop bankCardShop(Shop shop) {
        BankCard bankCard = bankCardRepo.findByCardNo(shop.getToCardNo())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"bu kart numarasına ait kart bulunamadı."+shop.getToCardNo()));
        double debt = shop.getMoney();
        Account account=bankCard.getAccount();
        if (bankCard.getMoney() >= debt) {
            bankCard.setMoney(bankCard.getMoney() - debt);
            account.setSumMoney(bankCard.getMoney());
            shop.setBankCard(bankCard);
            shop.setShopDate(LocalDate.now());
            shop.setMessage("kartınızdan " + debt + " TL alışveriş gerçekleştirildi.");
            return shopRepo.save(shop);
        }
        else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"bakiye yetersiz");
        }
    }
}
