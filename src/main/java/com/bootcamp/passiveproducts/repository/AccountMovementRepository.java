package com.bootcamp.passiveproducts.repository;

import com.bootcamp.passiveproducts.model.AccountMovement;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface AccountMovementRepository extends ReactiveCrudRepository<AccountMovement, String> {
    Flux<AccountMovement> findByAccountNumber(String num);
    Flux<AccountMovement> findByAmount(String num);

}