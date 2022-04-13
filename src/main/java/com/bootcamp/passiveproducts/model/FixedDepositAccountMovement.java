package com.bootcamp.passiveproducts.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document

public class FixedDepositAccountMovement {
    @Id
    private String id;
    private String accountNumber;
    private String movementType;
    private String movementDate;
    private String amount;
}
