package com.bootcamp.passiveproducts.service.impl;

import com.bootcamp.passiveproducts.model.AccountMovement;
import com.bootcamp.passiveproducts.repository.AccountMovementRepository;
import com.bootcamp.passiveproducts.service.AccountMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor

public class AccountMovementServiceImpl implements AccountMovementService {
    public final AccountMovementRepository repository;

    /**
     * Movimiento Retiro o deposito a cuenta corriente o Ahorro
     * @param accountMovement
     * @return
     */
    @Override
    public Mono<AccountMovement> create(AccountMovement accountMovement) {
        return repository.save(accountMovement);
    }

    @Override
    public Mono<AccountMovement> update(AccountMovement accountMovement) {
        return repository.save(accountMovement);
    }

    @Override
    public Mono deleteById(String id) {
        return repository.deleteById(id);
    }

    @Override
    public Mono delete(AccountMovement accountMovement) {
        return repository.delete(accountMovement);
    }

    @Override
    public Flux<AccountMovement> findAll() {
        return repository.findAll();
    }

    @Override
    public Flux<AccountMovement> findByAccountNumber(String mun) {
        return repository.findByAccountNumber(mun);
    }

}