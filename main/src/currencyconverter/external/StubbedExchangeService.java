package currencyconverter.external;

import currencyconverter.domain.entities.Money;
import currencyconverter.domain.external.ExchangeService;

import java.math.BigDecimal;

public class StubbedExchangeService implements ExchangeService {

    @Override
    public Money exchange(Money from, String toCurrency) {
        return new Money(BigDecimal.ONE, "USD");
    }
}
