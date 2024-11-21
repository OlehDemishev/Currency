package repository;

import model.Currency;

import java.util.Optional;

public interface CurrencyRepository {
    void save(Currency currency); // Сохранение валюты
    Optional<Currency> findByCode(String code); // Поиск валюты по коду
}