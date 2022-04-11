package com.bootcamp.passiveproducts.repository;

import com.bootcamp.passiveproducts.model.Account;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface AccountRepository extends ReactiveCrudRepository<Account, String> {
    Flux<Account> findByAccountNumber(String num);
}
