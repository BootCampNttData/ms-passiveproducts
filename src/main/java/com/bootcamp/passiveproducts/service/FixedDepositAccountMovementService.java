package com.bootcamp.passiveproducts.service;

import com.bootcamp.passiveproducts.model.FixedDepositAccountMovement;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FixedDepositAccountMovementService {
    Flux<FixedDepositAccountMovement> findAll();
    Mono<FixedDepositAccountMovement> create(FixedDepositAccountMovement fixedDepositAccountMovement);
    Flux<FixedDepositAccountMovement> findByidCreditCard(String id);
    Mono<FixedDepositAccountMovement> update(FixedDepositAccountMovement fixedDepositAccountMovement);
    Mono<FixedDepositAccountMovement> deleteById(String id);
    Mono delete(FixedDepositAccountMovement fixedDepositAccountMovement);
}
