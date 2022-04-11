package com.bootcamp.passiveproducts.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Account {
    @Id
    protected String id;
    protected String accountNumber;
    protected String accountType;
    protected String clientType;
    protected String creationDate;
    protected String clientId;
}
