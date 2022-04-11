package com.bootcamp.passiveproducts.controller;

import com.bootcamp.passiveproducts.model.FixedDepositAccountMovement;
import com.bootcamp.passiveproducts.service.FixedDepositAccountMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/fixedDepositAccountMovement")
@RequiredArgsConstructor
public class FixedDepositAccountMovementController {
    public final FixedDepositAccountMovementService service;
    @GetMapping
    public Flux<FixedDepositAccountMovement> getAll(){
        return service.findAll();
    }

    @GetMapping("/find/{num}")
    public Flux<FixedDepositAccountMovement> getByAccountNumber(@PathVariable("num") String num){
        return service.findByAccountNumber(num);
    }

    @PostMapping
    public Mono<FixedDepositAccountMovement> create(@RequestBody FixedDepositAccountMovement fixedDepositAccountMovementMovement){
        return service.create(fixedDepositAccountMovementMovement);
    }

    @PostMapping("/update")
    public Mono<FixedDepositAccountMovement> update(@RequestBody FixedDepositAccountMovement fixedDepositAccountMovementMovement){
        return service.create(fixedDepositAccountMovementMovement);
    }

    @DeleteMapping
    public Mono<FixedDepositAccountMovement> delete(@RequestBody FixedDepositAccountMovement fixedDepositAccountMovementMovement){
        return service.delete(fixedDepositAccountMovementMovement);
    }

    @DeleteMapping("/byId/{id}")
    public Mono<FixedDepositAccountMovement> deleteById(@RequestBody String id){
        return service.deleteById(id);
    }

}