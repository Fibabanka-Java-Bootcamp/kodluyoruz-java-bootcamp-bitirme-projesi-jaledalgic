package org.kodluyoruz.mybank.Debt;

import org.kodluyoruz.mybank.CreditCards.CreditCard;
import org.kodluyoruz.mybank.CreditCards.CreditCardRepository;
import org.kodluyoruz.mybank.Shopping.Shop;
import org.kodluyoruz.mybank.Shopping.ShopRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Component
public class DebtService {
    private final DebtRepository debtRepo;
    private final CreditCardRepository creditCardRepo;
    private final ShopRepository shopRepo;

    public DebtService(DebtRepository debtRepo, CreditCardRepository creditCardRepo, ShopRepository shopRepo) {
        this.debtRepo = debtRepo;
        this.creditCardRepo = creditCardRepo;
        this.shopRepo = shopRepo;
    }

    public void debtAdd(Shop shop) {

        Debt debt=new Debt();
        debt.setDebtMessage(shop.getMessage());
        debt.setDebtMoney(shop.getMoney());
        debt.setDebtDate(shop.getShopDate());
        debt.setCardNo(shop.getToCardNo());
        debt.setDebtDate(LocalDate.now());
        debtRepo.save(debt);
    }
    public Debt debtInquiry(UUID cardNo){

        return debtRepo.findByCardNo(cardNo)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"herhangi bir borç yok"));
    }

    public String  payment(Integer id, UUID cardNo, String paymentType, double amount) {
        String returnMessage=null;
        CreditCard creditCard = creditCardRepo.findByCardNo(cardNo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "creditcard not found"));
        if(debtRepo.existsById(id)) {
            if (paymentType.equals("bankamatik")) {
                creditCard.setDebt(creditCard.getDebt() - amount);
                creditCard.setBoundary(creditCard.getBoundary() + amount);
                debtRepo.deleteById(id);
                returnMessage = "ödeme işlemi başarılı";
            }
            if (paymentType.equals("hesap")) {
                creditCard.setDebt(creditCard.getDebt() - amount);
                creditCard.setBoundary(creditCard.getBoundary() + amount);
                creditCard.getAccount().setSumMoney(creditCard.getAccount().getSumMoney() - amount);
                debtRepo.deleteById(id);
                returnMessage = "ödeme işlemi başarılı";
            }
            if (returnMessage == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "paymentType wrong");
            }
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"bu id'ye ait bir borç yok");
        }
        return returnMessage;
    }
}
