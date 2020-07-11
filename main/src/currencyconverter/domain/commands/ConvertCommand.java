package currencyconverter.domain.commands;

import currencyconverter.domain.entities.Money;
import currencyconverter.domain.external.ExchangeService;
import currencyconverter.domain.io.Logger;

public class ConvertCommand implements Command<ConvertCommand.Input> {
    private final ExchangeService exchangeService;
    private final Logger logger;

    public ConvertCommand(ExchangeService exchangeService, Logger logger) {
        this.exchangeService = exchangeService;
        this.logger = logger;
    }

    @Override
    public void execute(Input input) {
        exchangeAndLog(input);
    }

    protected Money exchangeAndLog(Input input) {
        Money converted = exchangeService.exchange(input.getFrom(), input.getToCurrency());
        logger.logLine(converted.toString());
        return converted;
    }

    public static class Input extends EmptyInput {
        private final Money from;
        private final String toCurrency;

        public Input(Money from, String toCurrency) {
            this.from = from;
            this.toCurrency = toCurrency;
        }

        public Money getFrom() {
            return from;
        }

        public String getToCurrency() {
            return toCurrency;
        }
    }
}
