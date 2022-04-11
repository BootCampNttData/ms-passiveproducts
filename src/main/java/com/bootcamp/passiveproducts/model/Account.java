package com.bootcamp.passiveproducts.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Account {
    @Id
    public String id;
    public String accountNumber;
    public String accountType;
    public String feeAmount;
    public String movementLimit;
    public String clientType;
    public String creationDate;
    public String clientId;
}
