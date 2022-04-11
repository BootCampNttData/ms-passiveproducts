package com.bootcamp.passiveproducts.controller;

import com.bootcamp.passiveproducts.model.Account;
import com.bootcamp.passiveproducts.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    public final AccountService service;
    @GetMapping
    public Flux<Account> getAll(){
        return service.findAll();
    }

    @GetMapping("/find/{num}")
    public Flux<Account> getByIdCredit(@PathVariable("num") String num){
        return service.findByAccountNumber(num);
    }

    @PostMapping
    public Mono<Account> create(@RequestBody Account account){
        return service.create(account);
    }

    @PostMapping("/update")
    public Mono<Account> update(@RequestBody Account account){
        return service.create(account);
    }

    @DeleteMapping
    public Mono<Account> delete(@RequestBody Account account){
        return service.delete(account);
    }

    @DeleteMapping("/byId/{id}")
    public Mono<Account> deleteById(@RequestBody String id){
        return service.deleteById(id);
    }

}