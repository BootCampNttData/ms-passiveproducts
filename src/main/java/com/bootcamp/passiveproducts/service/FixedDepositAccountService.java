package com.bootcamp.passiveproducts.service;

import com.bootcamp.passiveproducts.model.FixedDepositAccount;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FixedDepositAccountService {
    Flux<FixedDepositAccount> findAll();
    Mono<FixedDepositAccount> create(FixedDepositAccount fixedDepositAccount);
    Flux<FixedDepositAccount> findByAccountNumber(String number);
    Mono<FixedDepositAccount> update(FixedDepositAccount fixedDepositAccount);
    Mono<FixedDepositAccount> deleteById(String id);
    Mono delete(FixedDepositAccount fixedDepositAccount);
}
