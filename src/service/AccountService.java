package service;

import model.Account;

import java.util.List;

public interface AccountService {
    void createAccount(int userId, String currencyCode); // Создание нового счета
    void depositFunds(int userId, String currencyCode, double amount); // Пополнение счета
    void withdrawFunds(int userId, String currencyCode, double amount); // Снятие средств
    List<Account> getAccounts(int userId); // Получение всех счетов пользователя
}