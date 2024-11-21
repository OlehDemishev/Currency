package test;

import model.Account;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.UserRepository;
import repository.UserRepositoryImpl;
import service.AccountService;
import service.AccountServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AccountServiceTest {
    private AccountService accountService;

    @BeforeEach
    public void setup() {
        UserRepository userRepository = new UserRepositoryImpl(null);
        accountService = new AccountServiceImpl(userRepository);
        User user = new User(1, "Alice", "alice@example.com", "password123", null);
        userRepository.save(user);
    }

    @Test
    public void testCreateAccount() {
        accountService.createAccount(1, "USD");
        List<Account> accounts = accountService.getAccounts(1);
        assertEquals(1, accounts.size());
        assertEquals("USD", accounts.get(0).getCurrencyCode());
    }

    @Test
    public void testDepositFunds() {
        accountService.createAccount(1, "USD");
        accountService.depositFunds(1, "USD", 100.0);
        List<Account> accounts = accountService.getAccounts(1);
        assertEquals(100.0, accounts.get(0).getBalance());
    }

    @Test
    public void testWithdrawFunds() {
        accountService.createAccount(1, "USD");
        accountService.depositFunds(1, "USD", 100.0);
        accountService.withdrawFunds(1, "USD", 50.0);
        List<Account> accounts = accountService.getAccounts(1);
        assertEquals(50.0, accounts.get(0).getBalance());
    }

    @Test
    public void testWithdrawInsufficientFunds() {
        accountService.createAccount(1, "USD");
        accountService.depositFunds(1, "USD", 100.0);
        assertThrows(IllegalArgumentException.class, () -> accountService.withdrawFunds(1, "USD", 150.0));
    }
}