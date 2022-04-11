package com.bootcamp.passiveproducts.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document

public class AccountMovement {
    @Id
    private String id;
    private String idCreditCad;
    private String movementType;
    private String movementDate;
    private String amount;
}
