package com.codedifferently.tankofamerica.domain.transaction.controllers;

import com.codedifferently.tankofamerica.domain.account.services.AccountService;
import com.codedifferently.tankofamerica.domain.transaction.model.Transaction;
import com.codedifferently.tankofamerica.domain.transaction.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class TransactionController {
    private TransactionService transactionService;
    private AccountService accountService;

    @Autowired
    public TransactionController(TransactionService transactionService, AccountService accountService) {
        this.transactionService = transactionService;
        this.accountService = accountService;
    }

    @ShellMethod("Get all transactions from account")
    public String accountTransactions(@ShellOption({"A", "-account"}) String accountId){
        return transactionService.getAllFromAccount(accountId);
    }
}