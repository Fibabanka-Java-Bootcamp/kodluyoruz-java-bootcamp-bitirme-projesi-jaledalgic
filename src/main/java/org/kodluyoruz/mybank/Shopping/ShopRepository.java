package org.kodluyoruz.mybank.Shopping;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface ShopRepository extends CrudRepository<Shop,Integer> {
    Iterable<Shop> findAll();
    List<Shop> findByToCardNo(UUID id);
}
