package repository;

import model.Currency;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CurrencyRepositoryImpl implements CurrencyRepository {
    private final Map<String, Currency> currencyStorage = new HashMap<>();

    @Override
    public void save(Currency currency) {
        currencyStorage.put(currency.getCode(), currency);
    }

    @Override
    public Optional<Currency> findByCode(String code) {
        return Optional.ofNullable(currencyStorage.get(code));
    }
}