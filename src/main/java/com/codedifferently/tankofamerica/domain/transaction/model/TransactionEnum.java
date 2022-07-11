package com.codedifferently.tankofamerica.domain.transaction.model;

public enum TransactionEnum {

    SUCCESFULL("SUCCESFUL"), PENDING("Pending"), FAILED("FAILED");

    public final String name;

    TransactionEnum(String name){
        this.name = name;
    }

    //pending
    //succesful
    //failed (throw transaction error)
}
