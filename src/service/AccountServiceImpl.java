package service;

import model.Account;
import model.User;
import repository.UserRepository;

import java.util.List;

public class AccountServiceImpl implements AccountService {
    private final UserRepository userRepository;

    public AccountServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createAccount(int userId, String currencyCode) {
        userRepository.findById(userId).ifPresent(user -> {
            Account account = new Account(currencyCode, 0);
            user.addAccount(account);
        });
    }

    @Override
    public void depositFunds(int userId, String currencyCode, double amount) {
        userRepository.findById(userId).ifPresent(user -> user.getAccounts().stream()
                .filter(account -> account.getCurrencyCode().equals(currencyCode))
                .findFirst()
                .ifPresentOrElse(
                        account -> account.deposit(amount),
                        () -> {
                            throw new IllegalArgumentException("Account not found");
                        }
                ));
    }

    @Override
    public void withdrawFunds(int userId, String currencyCode, double amount) {
        userRepository.findById(userId).ifPresent(user -> user.getAccounts().stream()
                .filter(account -> account.getCurrencyCode().equals(currencyCode))
                .findFirst()
                .ifPresentOrElse(
                        account -> account.withdraw(amount),
                        () -> {
                            throw new IllegalArgumentException("Account not found");
                        }
                ));
    }

    @Override
    public List<Account> getAccounts(int userId) {
        return userRepository.findById(userId)
                .map(User::getAccounts)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}