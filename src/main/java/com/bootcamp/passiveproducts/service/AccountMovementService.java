package com.bootcamp.passiveproducts.service;

import com.bootcamp.passiveproducts.model.AccountMovement;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountMovementService {
    Flux<AccountMovement> findAll();
    Mono<AccountMovement> create(AccountMovement accountMovement);
    Flux<AccountMovement> findByidCreditCad(String id);
    Mono<AccountMovement> update(AccountMovement accountMovement);
    Mono<AccountMovement> deleteById(String id);
    Mono delete(AccountMovement accountMovement);
}
