package com.bootcamp.passiveproducts.service.impl;

import com.bootcamp.passiveproducts.model.FixedDepositAccount;
import com.bootcamp.passiveproducts.repository.FixedDepositAccountRepository;
import com.bootcamp.passiveproducts.service.FixedDepositAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor

public class FixedDepositAccountServiceImpl implements FixedDepositAccountService {
    public final FixedDepositAccountRepository repository;

    @Override
    public Mono<FixedDepositAccount> create(FixedDepositAccount fixedDepositAccount) {
        return repository.save(fixedDepositAccount);
    }

    @Override
    public Mono<FixedDepositAccount> update(FixedDepositAccount fixedDepositAccount) {
        return repository.save(fixedDepositAccount);
    }

    @Override
    public Mono deleteById(String id) {
        return repository.deleteById(id);
    }

    @Override
    public Mono delete(FixedDepositAccount fixedDepositAccount) {
        return repository.delete(fixedDepositAccount);
    }

    @Override
    public Flux<FixedDepositAccount> findAll() {
        return repository.findAll();
    }

    @Override
    public Flux<FixedDepositAccount> findByAccountNumber(String number) {
        return repository.findByAccountNumber(number);
    }
}