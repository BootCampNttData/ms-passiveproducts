package com.bootcamp.passiveproducts.service.impl;

import com.bootcamp.passiveproducts.model.FixedDepositAccountMovement;
import com.bootcamp.passiveproducts.repository.FixedDepositAccountMovementRepository;
import com.bootcamp.passiveproducts.service.FixedDepositAccountMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor

public class FixedDepositAccountMovementServiceImpl implements FixedDepositAccountMovementService {
    public final FixedDepositAccountMovementRepository repository;

    @Override
    public Mono<FixedDepositAccountMovement> create(FixedDepositAccountMovement fixedDepositAccountMovement) {
        return repository.save(fixedDepositAccountMovement);
    }

    @Override
    public Mono<FixedDepositAccountMovement> update(FixedDepositAccountMovement fixedDepositAccountMovement) {
        return repository.save(fixedDepositAccountMovement);
    }

    @Override
    public Mono deleteById(String id) {
        return repository.deleteById(id);
    }

    @Override
    public Mono delete(FixedDepositAccountMovement fixedDepositAccountMovement) {
        return repository.delete(fixedDepositAccountMovement);
    }

    @Override
    public Flux<FixedDepositAccountMovement> findAll() {
        return repository.findAll();
    }

    @Override
    public Flux<FixedDepositAccountMovement> findByAccountNumber(String num) {
        return repository.findByAccountNumber(num);
    }
}