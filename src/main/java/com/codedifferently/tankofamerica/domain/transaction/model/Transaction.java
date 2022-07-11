package com.codedifferently.tankofamerica.domain.transaction.model;

import com.codedifferently.tankofamerica.domain.account.models.Account;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-char")
    private UUID id;

    private Double amount;

    @ManyToOne
    Account account;

    public Transaction() {
    }

    public Transaction(Double amount) {
        this.amount = amount;
    }

    public Transaction(Double amount, Account account) {
        this.amount = amount;
        this.account = account;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return String.format("Transaction Amount %.2f Account id: %s", amount, account.getId().toString());
    }
}