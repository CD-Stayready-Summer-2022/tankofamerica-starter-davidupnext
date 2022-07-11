package com.codedifferently.tankofamerica.domain.account.services;

import com.codedifferently.tankofamerica.domain.exceptions.UserNotFoundException;
import com.codedifferently.tankofamerica.domain.account.models.Account;
import com.codedifferently.tankofamerica.domain.exceptions.overDraftException;

public interface AccountService {
    Account create(Long userId, Account account) throws UserNotFoundException;
    Account getById(String userid);
    String getAllFromUser(Long userId) throws UserNotFoundException;
    Account update(Account account);
    void withdraw(Long userid, Double withdrawAmount) throws overDraftException,UserNotFoundException;
    void deposit (Long userid, Double depositAmount) throws UserNotFoundException;
    StringBuilder viewBalance(Long userId) throws UserNotFoundException;
    Boolean delete(String id);
}
