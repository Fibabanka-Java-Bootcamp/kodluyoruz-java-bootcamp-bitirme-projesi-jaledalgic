package org.kodluyoruz.mybank.Debt;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface DebtRepository extends CrudRepository<Debt,Integer> {

    Iterable<Debt> findAll();
    Optional<Debt> findByCardNo(UUID cardNo);
    void deleteById(Integer id);
    boolean existsById(UUID id);
}
