package com.bootcamp.passiveproducts.service.impl;

import com.bootcamp.passiveproducts.model.Account;
import com.bootcamp.passiveproducts.repository.AccountRepository;
import com.bootcamp.passiveproducts.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor

public class AccountServiceImpl implements AccountService {
    public final AccountRepository repository;

    @Override
    public Mono<Account> create(Account account) {
        return repository.save(account);

    }

    @Override
    public Mono<Account> update(Account account) {
        return repository.save(account);
    }

    @Override
    public Mono deleteById(String id) {
        return repository.deleteById(id);
    }

    @Override
    public Mono delete(Account account) {
        return repository.delete(account);
    }

    @Override
    public Flux<Account> findAll() {
        return repository.findAll();
    }

    @Override
    public Flux<Account> findByAccountNumber(String num) {
        return repository.findByAccountNumber(num);
    }

    @Override
    public Flux<Account> findByClientId(String num) {
        return repository.findByClientId(num);
    }
}