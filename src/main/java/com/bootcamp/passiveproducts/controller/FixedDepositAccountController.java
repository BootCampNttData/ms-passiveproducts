package com.bootcamp.passiveproducts.controller;

import com.bootcamp.passiveproducts.model.FixedDepositAccount;
import com.bootcamp.passiveproducts.service.FixedDepositAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/fixedDepositAccount")
@RequiredArgsConstructor
public class FixedDepositAccountController {
    public final FixedDepositAccountService service;
    @GetMapping
    public Flux<FixedDepositAccount> getAll(){
        return service.findAll();
    }

    @GetMapping("/find/{num}")
    public Flux<FixedDepositAccount> getByIdCredit(@PathVariable("num") String num){
        return service.findByAccountNumber(num);
    }

    @PostMapping
    public Mono<FixedDepositAccount> create(@RequestBody FixedDepositAccount fixedDepositAccount){
        return service.create(fixedDepositAccount);
    }

    @PostMapping("/update")
    public Mono<FixedDepositAccount> update(@RequestBody FixedDepositAccount fixedDepositAccount){
        return service.create(fixedDepositAccount);
    }

    @DeleteMapping
    public Mono<FixedDepositAccount> delete(@RequestBody FixedDepositAccount fixedDepositAccount){
        return service.delete(fixedDepositAccount);
    }

    @DeleteMapping("/byId/{id}")
    public Mono<FixedDepositAccount> deleteById(@RequestBody String id){
        return service.deleteById(id);
    }

}