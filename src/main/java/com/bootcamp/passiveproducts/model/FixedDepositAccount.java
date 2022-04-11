package com.bootcamp.passiveproducts.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class FixedDepositAccount {
    @Id
    private String id;
    private String accountNumber;
    private String depositDate;
    private String withdrawalDate;
    private String percent;
    private String months;
    private String clientId;
}
