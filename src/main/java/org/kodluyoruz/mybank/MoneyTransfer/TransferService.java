package org.kodluyoruz.mybank.MoneyTransfer;

import org.kodluyoruz.mybank.Accounts.Account;
import org.kodluyoruz.mybank.Accounts.AccountRepository;
import org.kodluyoruz.mybank.Accounts.AccountTypeEnum;
import org.kodluyoruz.mybank.BankCards.BankCardRepository;
import org.kodluyoruz.mybank.CreditCards.CreditCardRepository;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class TransferService {
    private final TransferRepository transferRepo;
    private final CreditCardRepository creditCardRepo;
    private final BankCardRepository bankCardRepo;
    private final AccountRepository accountRepo;
    private final RestTemplate restTemplate;


    public TransferService(TransferRepository transferRepo, CreditCardRepository creditCardRepo, BankCardRepository bankCardRepo, AccountRepository accountRepo, RestTemplateBuilder restTemplateBuilder) {
        this.transferRepo = transferRepo;
        this.creditCardRepo = creditCardRepo;
        this.bankCardRepo = bankCardRepo;
        this.accountRepo = accountRepo;
        this.restTemplate = restTemplateBuilder.rootUri("https://api.exchangeratesapi.io").build();;
    }


    public Transfer transaction(Transfer transfer){
        //Map<String, String> parameters = new HashMap<>();
        //String[] rates = restTemplate.getForObject("/latest?base=TRY", String[].class, parameters);
        UUID fromIban=transfer.getFromIban();
        UUID toIban=transfer.getToIban();
        double amount=transfer.getAmount();
        boolean isToAccount=accountRepo.existsById(toIban);
        boolean isFromAccount=accountRepo.existsById(fromIban);
      if(isFromAccount){
          Account fromAccount=accountRepo.findByIban(fromIban);
          if(fromAccount.getSumMoney()>=amount) {
            if (isToAccount) {
                if (fromAccount.getAccountType().equals(AccountTypeEnum.birikim) || fromAccount.getAccountType().equals(AccountTypeEnum.vadesiz)) {
                    fromAccount.setSumMoney(fromAccount.getSumMoney() - amount);
                    Account toAccount=accountRepo.findByIban(toIban);
                    toAccount.setSumMoney(toAccount.getSumMoney()+amount);
                    bankCardRepo.findByAccount(fromAccount).setMoney(fromAccount.getSumMoney());
                    bankCardRepo.findByAccount(toAccount).setMoney(toAccount.getSumMoney());
                    transfer.setTransferDate(LocalDate.now());
                    transfer.setTransferFee(0);
                    transfer.setMessage(amount + " TL havale yapıldı.");
                }
            } else if (fromAccount.getAccountType().equals(AccountTypeEnum.vadesiz)) {

                transfer.setTransferFee(2);
                transfer.setTransferDate(LocalDate.now());
                fromAccount.setSumMoney(fromAccount.getSumMoney() - (amount+2));
                bankCardRepo.findByAccount(fromAccount).setMoney(fromAccount.getSumMoney());
                transfer.setMessage(amount + " TL EFT işlemi gerçekleştirildi.");
            }
            else{
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"birikim hesabınızdan başka bankaya transfer yapılamaz.");
            }
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"bakiye yetersiz");
        }}else {
          throw new ResponseStatusException(HttpStatus.NOT_FOUND,"account not found"+fromIban);
      }
        return transferRepo.save(transfer);
    }
}
