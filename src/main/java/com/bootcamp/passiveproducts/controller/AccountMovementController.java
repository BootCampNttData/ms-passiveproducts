package com.bootcamp.passiveproducts.controller;

import com.bootcamp.passiveproducts.model.AccountMovement;
import com.bootcamp.passiveproducts.service.AccountMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/accountMovement")
@RequiredArgsConstructor
public class AccountMovementController {
    public final AccountMovementService service;
    @GetMapping
    public Flux<AccountMovement> getAll(){
        return service.findAll();
    }

    @GetMapping("/find/{num}")
    public Flux<AccountMovement> getByIdCredit(@PathVariable("num") String num){
        return service.findByidCreditCad(num);
    }

    @PostMapping
    public Mono<AccountMovement> create(@RequestBody AccountMovement accountMovement){
        return service.create(accountMovement);
    }

    @PostMapping("/update")
    public Mono<AccountMovement> update(@RequestBody AccountMovement accountMovement){
        return service.create(accountMovement);
    }

    @DeleteMapping
    public Mono<AccountMovement> delete(@RequestBody AccountMovement accountMovement){
        return service.delete(accountMovement);
    }

    @DeleteMapping("/byId/{id}")
    public Mono<AccountMovement> deleteById(@RequestBody String id){
        return service.deleteById(id);
    }

}