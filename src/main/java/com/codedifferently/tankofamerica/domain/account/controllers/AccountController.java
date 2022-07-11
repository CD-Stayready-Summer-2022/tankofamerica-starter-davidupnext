package com.codedifferently.tankofamerica.domain.account.controllers;

import com.codedifferently.tankofamerica.domain.exceptions.UserNotFoundException;
import com.codedifferently.tankofamerica.domain.account.models.Account;
import com.codedifferently.tankofamerica.domain.account.services.AccountService;
import com.codedifferently.tankofamerica.domain.exceptions.overDraftException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @ShellMethod("Create new Account")
    public String createNewAccount (@ShellOption({"-U","--user"}) Long id,
                                    @ShellOption({"-N", "--name"}) String name,
                                    @ShellOption({"-B","--balance"})Double balance){
        try {
            Account account = new Account(name);
            account = accountService.create(id, account);
            return account.toString();
        } catch (UserNotFoundException e) {
            return "The User Id is invalid";
        }
    }

    @ShellMethod("Get User Accounts")
    public String userAccounts(@ShellOption({"-U","--user"}) Long id){

        try{
            String accounts = accountService.getAllFromUser(id);
            return accounts;
        }catch (UserNotFoundException e){
            return "The User Id is invalid";
        }

    }

    @ShellMethod("Deposit money in Account")
    public void deposit(@ShellOption({"-U", "--user"}) Long userId, @ShellOption({"-D", "--Deposit Amount"}) Double depositAmount) throws UserNotFoundException {
        try {
            accountService.deposit(userId, depositAmount);
        }catch (UserNotFoundException e){
            throw new UserNotFoundException("The User Id is invalid");
        }
    }

    @ShellMethod("Withdraw money from Account")
    public void withdraw(@ShellOption({"-U", "--user"})Long userId, @ShellOption({"-W", "--Withdraw Amount"}) Double withdrawAmount) throws UserNotFoundException {
        try {
            accountService.withdraw(userId, withdrawAmount);
        }catch (overDraftException e){
            throw new UserNotFoundException("The User Id is invalid");
        }
    }

}
