package org.kodluyoruz.mybank.CreditCards;

import org.kodluyoruz.mybank.Accounts.Account;
import org.kodluyoruz.mybank.Customers.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CreditCardRepository extends CrudRepository<CreditCard, UUID> {
    Iterable<CreditCard> findAll();

    Optional<CreditCard> findById(UUID uuid);
       boolean existsCreditCardByAccount(Account account);
       CreditCard findByAccount_Iban(UUID id);
       Optional<CreditCard> findByCardNo(UUID id);
       List<CreditCard> findByCustomer_Id(Integer id);
       boolean existsByCustomer_Id(Integer id);


}
