package com.codedifferently.tankofamerica.account.accounttest;

import com.codedifferently.tankofamerica.domain.account.models.Account;
import com.codedifferently.tankofamerica.domain.account.repos.AccountRepo;
import com.codedifferently.tankofamerica.domain.exceptions.overDraftException;
import com.codedifferently.tankofamerica.domain.user.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT + ".enabled=false"
})
@ExtendWith(SpringExtension.class)
public class AccountTest {

    @Autowired
    private User user;
    private Account account;

    @MockBean
    AccountRepo accountrepo;

    @BeforeEach
    public void setUp() {
        user = new User("David", "Adeleke", "d@g.com", "pass");
        user.setId(1L);
        account = new Account("checkings", user);
        account.setId(UUID.fromString("6dd85d22-aebf-4235-bea2-1dd66e8c0bc9"));
    }

    @Test
    @DisplayName("Created account test")
    public void createdAccountTest01(){
        String expected= "Account for David named checkings with id 6dd85d22-aebf-4235-bea2-1dd66e8c0bc9";
        String actual = account.toString();
        Assertions.assertEquals(expected,actual);
    }
    @Test
    @DisplayName("add money to bank account")
    public void newBalanceTest01() throws overDraftException {
        account.newBalance(250.0);
        Double expected = 250.0;
        Double actual = account.getBalance();
        Assertions.assertEquals(expected,actual);
        Assertions.assertEquals(expected,actual);
    }
    @Test
    @DisplayName("catch negative value (overDraftException")
    public void overDraftExcetpionTest01() {
        Assertions.assertThrows(overDraftException.class, () ->{
                account.newBalance(-250.0);
    });
    }
}
