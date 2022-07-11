package com.codedifferently.tankofamerica.domain.account.services;


import com.codedifferently.tankofamerica.domain.exceptions.UserNotFoundException;
import com.codedifferently.tankofamerica.domain.account.models.Account;
import com.codedifferently.tankofamerica.domain.account.repos.AccountRepo;
import com.codedifferently.tankofamerica.domain.exceptions.overDraftException;
import com.codedifferently.tankofamerica.domain.user.models.User;
import com.codedifferently.tankofamerica.domain.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{

    private AccountRepo accountRepo;
    private UserService userService;

    @Autowired
    public AccountServiceImpl(AccountRepo accountRepo, UserService userService) {
        this.accountRepo = accountRepo;
        this.userService = userService;
    }

    @Override
    public Account create(Long userId, Account account) throws UserNotFoundException {
        User owner = userService.getById(userId);
        account.setOwner(owner);
        return accountRepo.save(account);
    }

    @Override
    public Account getById(String id) {
        return null;
    }

    @Override
    public String getAllFromUser(Long userId) throws UserNotFoundException {
        StringBuilder builder = new StringBuilder();
        User owner = userService.getById(userId);
        List<Account> accounts = accountRepo.findByOwner(owner);
        for (Account account: accounts) {
            builder.append(account.toString() + "\n");
        }
        return builder.toString().trim();
    }

    @Override
    public Account update(Account account) {
        accountRepo.save(account);
        return account;
    }

    @Override
    public void withdraw(Long userId, Double withdrawAmount) throws overDraftException,UserNotFoundException {
        User owner = userService.getById(userId);
        List<Account> accounts = accountRepo.findByOwner(owner);
        Long ownderId = owner.getId();
        for (Account account: accounts ){
            if (owner.getId() == userId){
                if(account.getBalance()>=withdrawAmount){
                    account.setBalance(account.getBalance() - withdrawAmount);
                }
                else {
                    throw new overDraftException("Insuffiecient funds");
                }
            }
        }

    }
    @Override
    public void deposit(Long userId, Double depositAmount) throws UserNotFoundException {
        User owner = userService.getById(userId);
        List<Account> accounts = accountRepo.findByOwner(owner);
        Long ownderId = owner.getId();
        for (Account account: accounts ){
            if (owner.getId() == userId){
                    account.setBalance(account.getBalance() + depositAmount);
                }

            }
        }

    @Override
    public StringBuilder viewBalance(Long userId) throws UserNotFoundException {
        String view = "";
        StringBuilder builder = new StringBuilder(view);
        User owner = userService.getById(userId);
        List<Account> accounts = accountRepo.findByOwner(owner);
        Long ownderId = owner.getId();
        for (Account account: accounts ){
            if (owner.getId() == userId){
                builder = builder.append(account.getBalance());
            }
        }
        return builder;
    }

    @Override
    public Boolean delete(String id) {
        return null;
    }
}