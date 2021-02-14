package org.kodluyoruz.mybank.Customers;

import org.kodluyoruz.mybank.Accounts.Account;
import org.kodluyoruz.mybank.Accounts.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class CustomerService {
    private final CustomerRepository customerRepo;
    private final AccountRepository accountRepo;

    public CustomerService(CustomerRepository customerRepo, AccountRepository accountRepo) {
        this.customerRepo = customerRepo;
        this.accountRepo = accountRepo;
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
        boolean isAccount=accountRepo.existsByCustomer_Id(id);
        if(isAccount) {
            List<Account> accounts = accountRepo.findByCustomer_Id(id);

      int count=0;
      if(accounts.size()>1) {
          if (accounts.get(0).getSumMoney() == 0 && accounts.get(1).getSumMoney() == 0) {
              customerRepo.deleteById(id);
              accountRepo.deleteByCustomer_Id(id);
          }
      }else if(accounts.size()==1 && accounts.get(0).getSumMoney()==0){
          customerRepo.deleteById(id);
          accountRepo.deleteByCustomer_Id(id);

      }
      else{
          throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"müşterinin hesabında para bulunuyor.");
      }
        }else {
            customerRepo.deleteById(id);
            accountRepo.deleteByCustomer_Id(id);

        }
      }



}
