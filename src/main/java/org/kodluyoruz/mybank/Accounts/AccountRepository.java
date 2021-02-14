package org.kodluyoruz.mybank.Accounts;

import org.kodluyoruz.mybank.BankCards.BankCard;
import org.kodluyoruz.mybank.Customers.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface AccountRepository extends CrudRepository<Account, UUID> {
    List<Account> findAllBy();
    boolean existsById(UUID iban);
    void deleteById(UUID iban);
    void deleteByCustomer_Id(Integer id);

    boolean existsAccountByCustomer(Customer customer);
    List<Account> findByCustomer_Id(Integer id);
    boolean existsByCustomer_Id(Integer id);

    Account findByIban(UUID id);


}
