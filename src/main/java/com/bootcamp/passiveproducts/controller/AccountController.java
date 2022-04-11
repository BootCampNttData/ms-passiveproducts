package com.bootcamp.passiveproducts.controller;

import com.bootcamp.passiveproducts.model.Account;
import com.bootcamp.passiveproducts.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

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

    /**
     * Crea una nueva cuenta Ahorro o Corriente dependiendo del pararameto ingresado en AccountType [C|A]
     * Tambien valida si el cliente es una Empresa [E] o persona natural [P]
     * En caso que no se cumplan las condiciones retornara un objeto vacio.
     * @param account
     * @return
     */
    @PostMapping
    public Mono<Account> create(@RequestBody Account account){
        String accountType=account.getAccountType();
        String clientType = account.getClientType();
        if("P".equals(clientType)){
            List<Account> accountsList= service.findByClientId(account.getClientId()).collectList().block();
            boolean haveSvAcc=false;
            boolean haveCuAcc=false;
            for(Account a:accountsList){
                if(a.getAccountType().equals("C"))
                    haveCuAcc = true;
                if (a.getAccountType().equals("A"))
                    haveSvAcc = true;
            }
            if(account.getAccountType().equals("A") && !haveSvAcc){
                return service.create(account);
            }
            if(account.getAccountType().equals("C") && !haveCuAcc){
                return service.create(account);
            }
        }else {
            if ("C".equals(accountType)) {
                return service.create(account);
            }
        }
        return Mono.just(new Account());
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

    /** *****************************************/




}