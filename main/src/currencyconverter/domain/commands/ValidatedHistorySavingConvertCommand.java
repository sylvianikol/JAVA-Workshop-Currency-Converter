package currencyconverter.domain.commands;

import currencyconverter.domain.external.CurrencyValidator;
import currencyconverter.domain.external.ExchangeService;
import currencyconverter.domain.io.Logger;
import currencyconverter.domain.repository.ConversionHistoryRepository;

public class ValidatedHistorySavingConvertCommand extends HistorySavingConvertCommand{
    private CurrencyValidator validator;

    public ValidatedHistorySavingConvertCommand(ExchangeService exchangeService,
                                                Logger logger,
                                                ConversionHistoryRepository repo,
                                                CurrencyValidator validator) {
        super(exchangeService, logger, repo);
        this.validator = validator;
    }

    @Override
    public void execute(Input input) {
        validator.validate(input.getFrom().getCurrency());
        validator.validate(input.getToCurrency());
        super.execute(input);
    }
}
