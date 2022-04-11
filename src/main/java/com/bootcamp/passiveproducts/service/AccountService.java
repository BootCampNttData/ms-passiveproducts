package com.bootcamp.passiveproducts.service;

import com.bootcamp.passiveproducts.model.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountService {
    Flux<Account> findAll();
    Mono<Account> create(Account account);
    Flux<Account> findByAccountNumber(String num);
    Mono<Account> update(Account account);
    Mono<Account> deleteById(String id);
    Mono delete(Account account);
}
