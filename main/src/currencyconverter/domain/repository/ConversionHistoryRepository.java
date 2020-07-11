package currencyconverter.domain.repository;

import currencyconverter.domain.entities.Money;

import java.util.List;

public interface ConversionHistoryRepository {
    List<String> getLast(int n);

    void save(Money from, Money to);
}
