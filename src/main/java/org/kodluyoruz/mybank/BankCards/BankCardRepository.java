package org.kodluyoruz.mybank.BankCards;

import org.kodluyoruz.mybank.Accounts.Account;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;
import java.util.UUID;

public interface BankCardRepository extends CrudRepository<BankCard, UUID> {
    Optional<BankCard> findById(UUID uuid);
    boolean existsBankCardByAccount(Account account);
    BankCard findByAccount_AccountNumber(UUID id);
    Optional<BankCard> findByCardNo(UUID id);
    BankCard findByAccount(Account account);

}
