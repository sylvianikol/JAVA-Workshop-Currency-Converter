package currencyconverter.domain.external;

import currencyconverter.domain.exceptions.InvalidCurrencyException;

public interface CurrencyValidator {
    void validate(String currency) throws InvalidCurrencyException;
}
