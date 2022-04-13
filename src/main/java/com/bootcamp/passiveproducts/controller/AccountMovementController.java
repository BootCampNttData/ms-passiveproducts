package com.bootcamp.passiveproducts.controller;

import com.bootcamp.passiveproducts.model.AccountMovement;
import com.bootcamp.passiveproducts.model.AccountMovement;
import com.bootcamp.passiveproducts.service.AccountMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/accountMovement")
@RequiredArgsConstructor
public class AccountMovementController {
    @Value("${passiveproducts.server.url}")
    private String passPrdUrl;
    
    public final AccountMovementService service;
    @GetMapping
    public Flux<AccountMovement> getAll(){
        return service.findAll();
    }

    @GetMapping("/find/{num}")
    public Flux<AccountMovement> getByaccountNumber(@PathVariable("num") String num){
        return service.findByAccountNumber(num);
    }

    @GetMapping("/accountBalance/{account}")
    public String getAccountBalance(@PathVariable("account") String account){
        double balance=0;
        RestTemplate restTemplate=new RestTemplate();
        String urlDp = passPrdUrl +"/accountMovement/find/" + account;
        ResponseEntity<AccountMovement[]> accountMovements = restTemplate.getForEntity(urlDp,AccountMovement[].class);
        for(AccountMovement am: accountMovements.getBody()){
            if (am.getMovementType().equals("D")){
                balance += Double.parseDouble(am.getAmount());
            }else if (am.getMovementType().equals("R")){
                balance -= Double.parseDouble(am.getAmount());
            }
        }
        return String.valueOf(balance);
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