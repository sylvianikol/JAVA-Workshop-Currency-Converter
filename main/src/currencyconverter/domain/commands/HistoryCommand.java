package currencyconverter.domain.commands;

import currencyconverter.domain.io.Logger;
import currencyconverter.domain.repository.ConversionHistoryRepository;

import java.util.List;

public class HistoryCommand implements Command<HistoryCommand.Input> {
    private final ConversionHistoryRepository repo;
    private Logger logger;

    public HistoryCommand(ConversionHistoryRepository repo,
                          Logger logger) {
        this.repo = repo;
        this.logger = logger;
    }

    @Override
    public void execute(Input input) {
        List<String> lastNConversions = repo.getLast(input.getNumbersToShow());
        lastNConversions.forEach(cmd -> logger.logLine(cmd));
    }

    public static class Input extends EmptyInput {
        private final int numbersToShow;

        public Input(int numbersToShow) {
            if (numbersToShow < 1) {
                throw new IllegalArgumentException("Input must be a positive integer!");
            }
            this.numbersToShow = numbersToShow;
        }

        public int getNumbersToShow() {
            return numbersToShow;
        }
    }
}
