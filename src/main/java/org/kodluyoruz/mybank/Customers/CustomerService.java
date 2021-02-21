package org.kodluyoruz.mybank.Customers;

import org.kodluyoruz.mybank.Accounts.Account;
import org.kodluyoruz.mybank.Accounts.AccountRepository;
import org.kodluyoruz.mybank.CreditCards.CreditCard;
import org.kodluyoruz.mybank.CreditCards.CreditCardRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class CustomerService {
    private final CustomerRepository customerRepo;
    private final AccountRepository accountRepo;
    private final CreditCardRepository creditCardRepo;

    public CustomerService(CustomerRepository customerRepo, AccountRepository accountRepo, CreditCardRepository creditCardRepo) {
        this.customerRepo = customerRepo;
        this.accountRepo = accountRepo;
        this.creditCardRepo = creditCardRepo;
    }

    public Customer create(Customer customer) {
        return customerRepo.save(customer);
    }

    public Customer update(Customer customer, Integer id) {
        Customer customer1=customerRepo.findById(id)
             .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Customer not found"+id));
            customer1=customer.toCustomerDto().toCustomer();
            customer1.setId(id);
            return customerRepo.save(customer1);
    }
    public void delete(Integer id){
        int count1=0;
        int count2=0;
        boolean isAccount=accountRepo.existsByCustomer_Id(id);
        boolean isCreditCard=creditCardRepo.existsByCustomer_Id(id);
        if(isAccount && isCreditCard) {
            List<Account> accounts = accountRepo.findByCustomer_Id(id);
            List<CreditCard> creditCards=creditCardRepo.findByCustomer_Id(id);
          for(int i=0;i<accounts.size();i++){
              if(accounts.get(i).getSumMoney()==0){
                  count1++;
              }
          }for(int i=0;i<creditCards.size();i++){
                if(creditCards.get(i).getDebt()==0){
                    count2++;
                }
            }
          if(count1==accounts.size() && count2==creditCards.size()){
              customerRepo.deleteById(id);
              throw new ResponseStatusException(HttpStatus.NO_CONTENT,"müşteri silindi.");
          }
          else if(count1!=accounts.size()){
              throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"müşterinin hesabında para bulunuyor.");
          }
          else{
           throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"müşterinin kredi kartı borcu bulunuyor.");
          }
        }else {
            customerRepo.deleteById(id);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT,"müşteri silindi.");
        }
      }



}
