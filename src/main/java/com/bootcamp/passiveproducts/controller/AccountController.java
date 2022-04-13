package com.bootcamp.passiveproducts.controller;

import com.bootcamp.passiveproducts.model.Account;
import com.bootcamp.passiveproducts.service.AccountService;
import com.sun.jdi.connect.Connector;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    @Value("${passiveproducts.server.url}")
    private String passPrdUrl;

    public final AccountService service;
    @GetMapping
    public Flux<Account> getAll(){
        return service.findAll();
    }

    @GetMapping("/accountNumber/{num}")
    public Flux<Account> findByAccountNumber(@PathVariable("num") String num){
        return service.findByAccountNumber(num);
    }

    /**
     * Crea una nueva cuenta Ahorro o Corriente dependiendo del pararameto ingresado en AccountType [C|A]
     * Tambien valida si el cliente es una Empresa [E] o persona natural [P]
     * En caso que no se cumplan las condiciones retornara un objeto vacio y no se almacenara en la BD.
     * @param account
     * @return
     */
    @PostMapping
    public Mono<Account> create(@RequestBody Account account){
        String accountType=account.getAccountType();
        String clientType = account.getClientType();
        RestTemplate restTemplate=new RestTemplate();
        Mono<Account> mono=Mono.just(new Account());

        if("P".equals(clientType)){                                                      /** Si es cuenta Personal */
            boolean haveSavingAcc=false;
            boolean haveCurrentAcc=false;

            String url = passPrdUrl +"/account/findByClientId/" + account.getClientId();
            ResponseEntity<Account[]> accounts = restTemplate.getForEntity(url,Account[].class);

            for(Account a: accounts.getBody()){
                if(a.getAccountType().equals("A")){ haveSavingAcc=true; }
                if(a.getAccountType().equals("C")){ haveCurrentAcc=true; }
            }

            if(account.getAccountType().equals("A") && !haveSavingAcc) { mono =  service.create(account); }
            if(account.getAccountType().equals("C") && !haveCurrentAcc){ mono =  service.create(account); }

        }else{                                                                          /** Si es cuenta Corporativa */
            if ("C".equals(accountType)) { mono = service.create(account); }
        }
        return mono;
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
    @GetMapping("/findByClientId/{id}")
    public Flux<Account> findByClientId(@PathVariable("id") String id){
        return service.findByClientId(id);
    }



}