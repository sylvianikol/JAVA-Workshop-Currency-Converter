package currencyconverter.console;

import currencyconverter.domain.commands.*;
import currencyconverter.domain.entities.Money;
import currencyconverter.domain.external.CurrencyValidator;
import currencyconverter.domain.external.ExchangeService;
import currencyconverter.domain.io.Logger;
import currencyconverter.domain.repository.ConversionHistoryRepository;
import currencyconverter.external.CurrConvExchangeService;
import currencyconverter.external.CurrencyValidatorFromFile;
import currencyconverter.external.StubbedExchangeService;
import currencyconverter.repository.InMemoryConversionHistoryRepository;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.Scanner;

public class ConsoleRunner {
    private static ExchangeService exchangeService = new CurrConvExchangeService();
    private static Logger logger = new ConsoleLogger();
    private static ConversionHistoryRepository repo = new InMemoryConversionHistoryRepository();

    public void run() {
        Scanner scanner = new Scanner(System.in);
        ExchangeService exchangeService = new CurrConvExchangeService();
        Logger logger = new ConsoleLogger();
        // change with ConversionHistoryRepository conversionHistoryRepo = new FileConversionHistoryRepository(Path.of("file path here")) after completing TODO
        ConversionHistoryRepository conversionHistoryRepo = new InMemoryConversionHistoryRepository();
        CurrencyValidator currencyValidator = new CurrencyValidatorFromFile(
                Path.of("D:\\Programming\\JAVA OOP\\14. Workshop\\src\\currencyconverter"));

        ConsoleCommandExecutor executor = new ConsoleCommandExecutor(
                conversionHistoryRepo,
                logger,
                exchangeService,
                currencyValidator
        );

        while (true) {
            String line = scanner.nextLine();
            String[] tokens = line.split("\\s+");
            executor.execute(tokens);
        }
    }
}
