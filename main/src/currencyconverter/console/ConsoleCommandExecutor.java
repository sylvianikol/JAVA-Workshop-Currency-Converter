package currencyconverter.console;

import currencyconverter.domain.commands.*;
import currencyconverter.domain.entities.Money;
import currencyconverter.domain.external.CurrencyValidator;
import currencyconverter.domain.external.ExchangeService;
import currencyconverter.domain.io.Logger;
import currencyconverter.domain.repository.ConversionHistoryRepository;

import java.math.BigDecimal;

public class ConsoleCommandExecutor {
    private ConversionHistoryRepository conversionHistoryRepo;
    private Logger logger;
    private ExchangeService exchangeService;
    private CurrencyValidator validator;

    public ConsoleCommandExecutor(ConversionHistoryRepository conversionHistoryRepo,
                                  Logger logger,
                                  ExchangeService exchangeService,
                                  CurrencyValidator validator) {
        this.conversionHistoryRepo = conversionHistoryRepo;
        this.logger = logger;
        this.exchangeService = exchangeService;
        this.validator = validator;
    }

    public void execute(String[] args) {
        switch (args[0]) {
            case "END":
                end();
                break;
            case "CONVERT":
                convert(args);
                break;
            case "HISTORY":
                history(args);
                break;
            default:
                logger.logLine("Incorrect command!");
                break;
        }
    }

    private void end() {
        new EndCommand().execute(new EmptyInput());
    }

    private void convert(String[] tokens) {
        BigDecimal fromValue = new BigDecimal(tokens[1]);
        String fromCurrency = tokens[2];
        String toCurrency = tokens[3];

        ConvertCommand.Input input = new ConvertCommand.Input(
                new Money(fromValue, fromCurrency),
                toCurrency
        );

        new ValidatedHistorySavingConvertCommand(
                exchangeService,
                logger,
                conversionHistoryRepo,
                validator).execute(input);
    }

    private void history(String[] tokens) {
        Command<HistoryCommand.Input> cmd = new HistoryCommand(conversionHistoryRepo, logger);
        cmd.execute(new HistoryCommand.Input(Integer.parseInt(tokens[1])));
    }
}
