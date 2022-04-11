package com.bootcamp.passiveproducts.repository;

import com.bootcamp.passiveproducts.model.FixedDepositAccount;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface FixedDepositAccountRepository extends ReactiveCrudRepository<FixedDepositAccount, String> {
    Flux<FixedDepositAccount> findByAccountNumber(String number);
}