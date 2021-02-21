package org.kodluyoruz.mybank.Debt;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DebtRepository extends CrudRepository<Debt,Integer> {

    Iterable<Debt> findAll();
    List<Debt> findByCardNo(UUID cardNo);
    void deleteById(Integer id);
    boolean existsById(UUID id);
}
