package org.kodluyoruz.mybank.MoneyTransfer;

import org.springframework.data.repository.CrudRepository;

public interface TransferRepository extends CrudRepository<Transfer,Integer> {
        Iterable<Transfer> findAll();
}
