package com.bootcamp.passiveproducts.controller;

import com.bootcamp.passiveproducts.model.FixedDepositAccount;
import com.bootcamp.passiveproducts.model.FixedDepositAccountMovement;
import com.bootcamp.passiveproducts.service.FixedDepositAccountMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/fixedDepositAccountMovement")
@RequiredArgsConstructor
public class FixedDepositAccountMovementController {
    @Value("${passiveproducts.server.url}")
    private String passPrdUrl;

    public final FixedDepositAccountMovementService service;
    @GetMapping
    public Flux<FixedDepositAccountMovement> getAll(){
        return service.findAll();
    }

    @GetMapping("/find/{num}")
    public Flux<FixedDepositAccountMovement> getByAccountNumber(@PathVariable("num") String num){
        return service.findByAccountNumber(num);
    }

    /**
     * Realiza deposito o retiro de cuenta a plazo fijo
     * @param fixedDepositAccountMovement
     * @return
     */
    @PostMapping
    public Mono<FixedDepositAccountMovement> create(@RequestBody FixedDepositAccountMovement fixedDepositAccountMovement){
        SimpleDateFormat dateFormat = new SimpleDateFormat ("dd/MM/yyyy");

        String mvDate=fixedDepositAccountMovement.getMovementDate();
        String mvType=fixedDepositAccountMovement.getMovementType();
        RestTemplate restTemplate=new RestTemplate();
        String urlDp = passPrdUrl +"/fixedDepositAccount/find/" + fixedDepositAccountMovement.getAccountNumber();
        ResponseEntity<FixedDepositAccount[]> fixedDepositAccount = restTemplate.getForEntity(urlDp,FixedDepositAccount[].class);

        String urlBl = passPrdUrl +"/fixedDepositAccountMovement/accountBalance/" + fixedDepositAccountMovement.getAccountNumber();
        ResponseEntity<String> balance = restTemplate.getForEntity(urlBl,String.class);


        String wdDate = fixedDepositAccount.getBody()[0].getWithdrawalDate();
        boolean allowWithdrar=false;
        try {
            Date movement = dateFormat.parse(mvDate);
            Date wihtdrawel = dateFormat.parse(wdDate);
            if(movement.before(wihtdrawel)){
                allowWithdrar=true;
            }
        }catch (Exception e){}

        if(mvType.equals("D")){
            return service.create(fixedDepositAccountMovement);    
        }else{
            /** Se valida la fecha de retiro */
            if(allowWithdrar && Double.parseDouble(fixedDepositAccountMovement.getAmount())<=Double.parseDouble(balance.getBody())){
                return service.create(fixedDepositAccountMovement);
            }
        }
        return Mono.just(new FixedDepositAccountMovement());
    }

    @PostMapping("/update")
    public Mono<FixedDepositAccountMovement> update(@RequestBody FixedDepositAccountMovement fixedDepositAccountMovement){
        return service.create(fixedDepositAccountMovement);
    }

    @DeleteMapping
    public Mono<FixedDepositAccountMovement> delete(@RequestBody FixedDepositAccountMovement fixedDepositAccountMovement){
        return service.delete(fixedDepositAccountMovement);
    }

    @DeleteMapping("/byId/{id}")
    public Mono<FixedDepositAccountMovement> deleteById(@RequestBody String id){
        return service.deleteById(id);
    }
    @GetMapping("/accountBalance/{account}")
    public String getAccountBalance(@PathVariable("account") String account){
        double balance=0;
        RestTemplate restTemplate=new RestTemplate();
        String urlDp = passPrdUrl +"/fixedDepositAccountMovement/find/" + account;
        ResponseEntity<FixedDepositAccountMovement[]> fixedDepositAccountMovements = restTemplate.getForEntity(urlDp,FixedDepositAccountMovement[].class);
        for(FixedDepositAccountMovement am: fixedDepositAccountMovements.getBody()){
            if (am.getMovementType().equals("D")){
                balance += Double.parseDouble(am.getAmount());
            }else{
                balance -= Double.parseDouble(am.getAmount());
            }
        }
        return String.valueOf(balance);
    }
}