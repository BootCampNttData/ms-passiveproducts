package com.bootcamp.passiveproducts.repository;

import com.bootcamp.passiveproducts.model.Account;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountRepository extends ReactiveCrudRepository<Account, String> {
    Flux<Account> findByAccountNumber(String num);
//    Mono<Account> findByClientIdAndAccountType(String num,String type);
    Flux<Account> findByClientId(String id);

}
