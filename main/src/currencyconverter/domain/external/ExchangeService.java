package currencyconverter.domain.external;

import currencyconverter.domain.entities.Money;

public interface ExchangeService {

    Money exchange(Money from, String toCurrency);
}
