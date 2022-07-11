package com.codedifferently.tankofamerica.domain.transaction.services;

import com.codedifferently.tankofamerica.domain.account.models.Account;
import com.codedifferently.tankofamerica.domain.exceptions.UserNotFoundException;
import com.codedifferently.tankofamerica.domain.exceptions.overDraftException;
import com.codedifferently.tankofamerica.domain.transaction.model.Transaction;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.UUID;

public interface TransactionService {
   Transaction create(String accountId, Transaction transaction) throws AccountNotFoundException, overDraftException;
   Transaction getById(Long id);
   String getAllFromAccount(String accountId);
   Boolean delete(String accountid);


}
