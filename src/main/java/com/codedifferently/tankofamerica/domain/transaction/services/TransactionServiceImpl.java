package com.codedifferently.tankofamerica.domain.transaction.services;

import com.codedifferently.tankofamerica.domain.account.models.Account;
import com.codedifferently.tankofamerica.domain.account.services.AccountService;
import com.codedifferently.tankofamerica.domain.exceptions.overDraftException;
import com.codedifferently.tankofamerica.domain.transaction.model.Transaction;

import com.codedifferently.tankofamerica.domain.transaction.repos.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepo transactionRepo;
    private AccountService accountService;

    @Autowired
    public TransactionServiceImpl(TransactionRepo transactionRepo,AccountService accountService){
        this.transactionRepo = transactionRepo;
        this.accountService = accountService;
    }

    @Override
    public Transaction create(String accountId, Transaction transaction) throws AccountNotFoundException, overDraftException {
      Double amount = transaction.getAmount();
      Account account = transaction.getAccount();
      account.newBalance(amount);
      account = accountService.update(account);
      transaction.setAccount(account);
      return transactionRepo.save(transaction);
    }

    @Override
    public Transaction getById(Long id) {
        return null;
    }

    @Override
    public String getAllFromAccount(String accountId) {
        StringBuilder builder = new StringBuilder();
        Account account = accountService.getById(accountId);
        List<Transaction> transactions = transactionRepo.findByAccount(account);
        for (Transaction transaction : transactions){
            builder.append(transaction.toString()+ "\n");
        }
        return builder.toString().trim();

    }

    @Override
    public Boolean delete(String accountid) {
        return null;
    }

    public TransactionRepo getTransactionRepo() {
        return transactionRepo;
    }

    public void setTransactionRepo(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}

