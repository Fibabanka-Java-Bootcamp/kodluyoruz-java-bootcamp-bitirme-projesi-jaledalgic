package org.kodluyoruz.mybank.Accounts;

import org.kodluyoruz.mybank.BankCards.BankCardRepository;
import org.kodluyoruz.mybank.CreditCards.CreditCardRepository;
import org.kodluyoruz.mybank.Customers.Customer;
import org.kodluyoruz.mybank.Customers.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Component
public class AccountService {
    private final AccountRepository accountRepo;
    private final CustomerRepository customerRepo;
    private final CreditCardRepository creditRepo;
    private final BankCardRepository bankCardRepo;

    public AccountService(AccountRepository accountRepo, CustomerRepository customerRepo, CreditCardRepository creditRepo, BankCardRepository bankCardRepo) {
        this.accountRepo = accountRepo;
        this.customerRepo = customerRepo;
        this.creditRepo = creditRepo;
        this.bankCardRepo = bankCardRepo;
    }

    public Account create(Integer id, Account account) throws ResponseStatusException {
        Customer customer = customerRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found" + id));
        List<Account> accounts = accountRepo.findByCustomer_Id(id);
        if (accounts.size() < 2) {
            if (accounts.size() == 0 || !accounts.get(0).getAccountType().equals(account.getAccountType())) {
                account.setCustomer(customer);
                account.setAccountNumber(UUID.randomUUID());
                return accountRepo.save(account);
            } else {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "müşterinin" + account.getAccountType() + " hesabı mevcut " );
            }
        }else
             throw new ResponseStatusException(HttpStatus.CONFLICT, "müşterinin iki hesabıda mevcut");

    }
    public void delete(UUID iban){
        if(accountRepo.existsById(iban)
                && accountRepo.findByIban(iban).getSumMoney()==0){
            accountRepo.deleteById(iban);
        }
    }
}
