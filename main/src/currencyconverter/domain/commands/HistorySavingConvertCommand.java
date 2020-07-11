package currencyconverter.domain.commands;

import currencyconverter.domain.entities.Money;
import currencyconverter.domain.external.ExchangeService;
import currencyconverter.domain.io.Logger;
import currencyconverter.domain.repository.ConversionHistoryRepository;

public class HistorySavingConvertCommand extends ConvertCommand {
    private ConversionHistoryRepository repo;

    public HistorySavingConvertCommand(ExchangeService exchangeService,
                                       Logger logger,
                                       ConversionHistoryRepository repo) {
        super(exchangeService, logger);
        this.repo = repo;
    }

    @Override
    public void execute(Input input) {
        Money converted = exchangeAndLog(input);
        repo.save(input.getFrom(), converted);
    }
}
